package ma.enset.Seance6.security.Service;

import ma.enset.Seance6.security.entities.AppRole;
import ma.enset.Seance6.security.entities.AppUser;

public interface SecurityService {
	AppUser saveUser(String username, String password, String verifyPassword);
	AppRole saveRole(String roleName, String description);
	void addRoleToUser(String username, String roleName);
	void removeRoleFromUser(String username, String roleName);
	AppUser loadUserByUsername(String username);
	
}
