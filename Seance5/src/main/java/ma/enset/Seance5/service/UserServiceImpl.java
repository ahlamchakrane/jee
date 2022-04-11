package ma.enset.Seance5.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.enset.Seance5.entities.Role;
import ma.enset.Seance5.entities.User;
import ma.enset.Seance5.repository.UserRepository;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private RoleService roleService;
	
	@Override
	public User addUser(User user) {
		user.setId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user= findUserByUsername(username);
		Role role= roleService.findRoleByRoleName(roleName);
		user.getRoles().add(role);
		role.getUsers().add(user);
	}

	@Override
	public User authenticate(String username, String password) {
		User user = userRepository.findByUsername(username);
		
		if(user==null) throw new RuntimeException("Bad credentials");
		if(user.getPassword().equals(password)) return user;
		throw new RuntimeException("Bad credentials");
	}

}
