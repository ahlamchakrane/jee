package ma.enset.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.enset.enteties.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByMalade(boolean malade);
	Page<Patient> findByMalade(boolean malade, Pageable pageable);
	List<Patient> findByMaladeAndScoreLessThan(boolean malade, int score);
	List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);
	List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date date1, Date date2,String nom);
	@Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
	List<Patient> chercherPatients(@Param("x") Date date1,@Param("y") Date date2, @Param("z") String nom);
	@Query("select p from Patient p where p.nom like :x and p.score < :y")
	List<Patient> chercherPatients2(@Param("x") String nom,@Param("y") int score);


	
}
