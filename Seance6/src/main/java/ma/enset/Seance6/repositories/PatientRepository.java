package ma.enset.Seance6.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.Seance6.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	Page<Patient> findByNomContains(String keyword, Pageable pageable);
}
