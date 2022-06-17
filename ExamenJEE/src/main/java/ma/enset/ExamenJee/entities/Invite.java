package ma.enset.ExamenJee.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Invite")
@Data @NoArgsConstructor @AllArgsConstructor
public class Invite extends Participent{
	private String affiliation;
	@OneToMany(mappedBy = "invite")
	private List<Inspiration> inspirations = new ArrayList<>();
}

