package ma.enset.Seance5.service;

import ma.enset.Seance5.entities.User;

public interface UserService {
	User addUser(User user);
	User findUserByUsername(String username);
	void addRoleToUser(String username, String rolename);
	User authenticate(String username, String password);
}
