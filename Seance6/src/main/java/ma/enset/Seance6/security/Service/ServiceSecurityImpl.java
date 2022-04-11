package ma.enset.Seance6.security.Service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.enset.Seance6.security.Repositories.AppRoleRepository;
import ma.enset.Seance6.security.Repositories.AppUserRepository;
import ma.enset.Seance6.security.entities.AppRole;
import ma.enset.Seance6.security.entities.AppUser;
@Service
@Slf4j //permet de loguer
@AllArgsConstructor
@Transactional
public class ServiceSecurityImpl implements SecurityService {
	private AppUserRepository appUserRepository;
	private AppRoleRepository appRoleRepository;
	private PasswordEncoder passwordEncoder;
	
//	public ServiceSecurityImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
//		this.appUserRepository = appUserRepository;
//		this.appRoleRepository = appRoleRepository;
//	}
	
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
	public AppRole saveRole(String roleName, String description) {
		AppRole appRole= appRoleRepository.findByRoleName(roleName);
		if(appRole!=null) throw new RuntimeException("Role "+ roleName+" already exists");
		appRole = new AppRole();
		appRole.setRoleName(roleName);
		appRole.setDescription(description);
		AppRole savedAppRole = appRoleRepository.save(appRole);
		return savedAppRole;
	}
	
	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser = appUserRepository.findByUsername(username);
		if(appUser==null) throw new RuntimeException("User not found");
		AppRole appRole= appRoleRepository.findByRoleName(roleName);
		if(appRole==null) throw new RuntimeException("Role not found");
		appUser.getAppRoles().add(appRole);
		
	}

	@Override
	public void removeRoleFromUser(String username, String roleName) {
		AppUser appUser = appUserRepository.findByUsername(username);
		if(appUser==null) throw new RuntimeException("User not found");
		AppRole appRole= appRoleRepository.findByRoleName(roleName);
		if(appRole==null) throw new RuntimeException("Role not found");
		appUser.getAppRoles().remove(appRole);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

}
