package ma.enset.ProjetJEE.services.userService;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import ma.enset.ProjetJEE.entities.AppUser;
import ma.enset.ProjetJEE.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private UserRepository appUserRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		this.appUserRepository = appUserRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public AppUser saveUser(String username, String password, String verifyPassword) {
		if(! password.equals(verifyPassword)) throw new RuntimeException("passwords not match");
		String hashedPWD = passwordEncoder.encode(password);
		AppUser appUser = new AppUser();
		appUser.setId(UUID.randomUUID().toString());
		appUser.setUsername(username);
		appUser.setPassword(hashedPWD);
		appUser.setActive(true);
		AppUser savedAppUser = appUserRepository.save(appUser);
		return savedAppUser;
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}
}
