package ma.enset.ActivitePratique.security.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ActivitePratique.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
	AppUser findByUsername(String username);
}
