package ma.enset.ExamenJee.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("Moderateur")
@Data @NoArgsConstructor @AllArgsConstructor
public class Moderateur extends Participent{
	private String specialité;
	@OneToMany(mappedBy = "moderateur")
	private List<Session> sessions = new ArrayList<>();
}


