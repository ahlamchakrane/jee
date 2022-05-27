package ma.enset.ProjetJEE.services.roleService;
import ma.enset.ProjetJEE.entities.AppRole;

public interface RoleService {
	AppRole saveRole(String roleName, String description);
	void addRoleToUser(String username, String roleName);
	void removeRoleFromUser(String username, String roleName);	
}
