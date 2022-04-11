package ma.enset.ActivitePratique.security.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ActivitePratique.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	AppRole findByRoleName(String roleName);
}
