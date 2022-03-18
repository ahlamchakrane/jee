package ma.enset.Seance5.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.enset.Seance5.entities.Role;
import ma.enset.Seance5.repository.RoleRepository;
@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements  RoleService{
	private RoleRepository roleRepository;
	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

}
