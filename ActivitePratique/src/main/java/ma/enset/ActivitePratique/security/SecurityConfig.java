package ma.enset.ActivitePratique.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//instancier en 1er lieu lors de démarrage de l application
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.enset.ActivitePratique.security.Service.UserDetailsServiceImpl;
@Configuration 
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
