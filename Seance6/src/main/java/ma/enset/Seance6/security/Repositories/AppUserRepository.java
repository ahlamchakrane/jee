package ma.enset.Seance6.security.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.Seance6.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
	AppUser findByUsername(String username);
}
