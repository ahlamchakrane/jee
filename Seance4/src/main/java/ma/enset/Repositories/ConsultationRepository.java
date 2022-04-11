package ma.enset.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.enteties.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
