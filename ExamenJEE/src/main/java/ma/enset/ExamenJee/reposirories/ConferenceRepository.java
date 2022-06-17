package ma.enset.ExamenJee.reposirories;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ExamenJee.entities.Conference;

public interface ConferenceRepository extends JpaRepository<Conference,Long> {

}
