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
@DiscriminatorValue("Speaker")
@Data @NoArgsConstructor @AllArgsConstructor
public class Speaker extends Participent{
	private String lien;
	@OneToMany(mappedBy = "speaker")
	private List<Conference> conferences = new ArrayList<>();
}

