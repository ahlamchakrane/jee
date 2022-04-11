package ma.enset.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.enteties.Medecin;


public interface MedecinRepository extends JpaRepository<Medecin, Long> {
	List<Medecin> findByNom(String nom);
}
