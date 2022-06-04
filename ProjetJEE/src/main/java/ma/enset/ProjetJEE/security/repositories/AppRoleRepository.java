package ma.enset.ProjetJEE.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ProjetJEE.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName (String roleName);
}
