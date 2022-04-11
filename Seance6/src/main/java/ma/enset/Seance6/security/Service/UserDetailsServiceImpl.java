package ma.enset.Seance6.security.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.enset.Seance6.security.entities.AppUser;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private SecurityService securityService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = securityService.loadUserByUsername(username);
// Methode 1
//		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		appUser.getAppRoles().forEach(role -> {
//			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
//			authorities.add(authority);
//		});
// Methode 2
		Collection<GrantedAuthority> authorities1 =
				appUser.getAppRoles()
						.stream()
						.map(role->new SimpleGrantedAuthority(role.getRoleName()))
						.collect(Collectors.toList());
//fin			
		User user = new User(appUser.getUsername(), appUser.getPassword(),authorities1);
		return user;
	}
	

	

}
