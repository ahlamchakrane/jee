package ma.enset.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.enteties.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByNom(String nom);
}
