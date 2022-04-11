package ma.enset.Seance5.service;

import ma.enset.Seance5.entities.Role;

public interface RoleService {
	Role addRole(Role role);
	Role findRoleByRoleName(String roleName);
}
