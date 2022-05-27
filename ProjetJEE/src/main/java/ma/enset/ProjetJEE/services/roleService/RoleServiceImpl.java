package ma.enset.ProjetJEE.services.roleService;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.enset.ProjetJEE.entities.AppRole;
import ma.enset.ProjetJEE.entities.AppUser;
import ma.enset.ProjetJEE.repositories.RoleRepository;
import ma.enset.ProjetJEE.repositories.UserRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	private RoleRepository appRoleRepository;
	private UserRepository appUserRepository;
	
		public RoleServiceImpl(RoleRepository appRoleRepository, UserRepository appUserRepository) {
		this.appRoleRepository = appRoleRepository;
		this.appUserRepository = appUserRepository;
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

	}
