package ma.enset.ExamenJee.reposirories;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.ExamenJee.entities.Session;

public interface SessionRepository extends JpaRepository<Session,Long> {

}
