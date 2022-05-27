package ma.enset.ProjetJEE.services.userService;

import ma.enset.ProjetJEE.entities.AppUser;

public interface UserService {
	AppUser saveUser(String username, String password, String verifyPassword);
	AppUser loadUserByUsername(String username);
	
}
