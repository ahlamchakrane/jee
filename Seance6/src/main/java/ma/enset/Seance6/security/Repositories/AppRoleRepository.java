package ma.enset.Seance6.security.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.Seance6.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	AppRole findByRoleName(String roleName);
}
