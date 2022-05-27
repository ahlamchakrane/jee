package ma.enset.ProjetJEE.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ProjetJEE.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{
	List<AppUser> findByUsernameContainsIgnoreCase(String username);
	AppUser findByUsername(String username);
}