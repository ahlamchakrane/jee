package ma.enset.ActivitePratique.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ActivitePratique.entities.Etudiant;


public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	Page<Etudiant> findByNomContains(String keyword, Pageable pageable);
}
