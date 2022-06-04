package ma.enset.ProjetJEE.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ProjetJEE.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
