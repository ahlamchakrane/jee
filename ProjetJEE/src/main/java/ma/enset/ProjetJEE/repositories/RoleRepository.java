package ma.enset.ProjetJEE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ProjetJEE.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{	
	AppRole findByRoleName(String roleName);

}