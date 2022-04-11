package ma.enset.Seance6.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//instancier en 1er lieu lors de démarrage de l application
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.enset.Seance6.security.Service.UserDetailsServiceImpl;
@Configuration 
// attribuer la securité à l app
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		/*
		// comment spring va chercher les roles et les utilisateurs : la strategie
//le plus simple c est memory Auth pour dire que les utilisateurs qui ont le droit d acceder à l app sont sotckés en memoire
		//cette methode pratique si nous faisons des tests
		//par defaut spring chiffre les mots de passe donc il faut les chifrés
		PasswordEncoder passwordEncoder = passwordEncoder();
		String encodedPWD = passwordEncoder.encode("1234"); // BCrypt algorithme
	
		authenticationManagerBuilder.inMemoryAuthentication()
		.withUser("user").password(encodedPWD).roles("USER")
		//.withUser("user").password("{noop}1234").roles("USER") = no password encoder
		.and()
		.withUser("admin").password(encodedPWD).roles("ADMIN","USER");
		*/
		
//JDBC authenticated
		//il faut créer 3 tables dans la BD 
		//table users avec username (primary key) password et active (prends soit 0 ou 1)
		//une table role avec 1 champs roleName(primary key)
		//et une table ussers_roles avec deux champs username(primary key) et role(primary key)
		//par defaut spring chiffre les mots de passe donc il faut les chifrés
		
		//pour inserer un utilisateur dans la table faire ceci pour trouver le password chiffré de 1234 puis ajouter un utilisateur avec le password trouvé 
			
			//String encodedPWD = passwordEncoder.encode("1234"); 
			//System.out.println(encodedPWD);
		/*
		authenticationManagerBuilder.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
			.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
			.rolePrefix("ROLE_")
			.passwordEncoder(passwordEncoder);
			*/
//user detail service
			authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl);
	}
	//pour spécifier les droits d'accés
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin(); //formulaire par defaut
		//si on veut specifier notre propre formulaire:
		// http.formLogin().("/login");
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		http.authorizeHttpRequests().anyRequest().authenticated(); // toute route necessite une authentification
		http.exceptionHandling().accessDeniedPage("/404");
	}
	
}
