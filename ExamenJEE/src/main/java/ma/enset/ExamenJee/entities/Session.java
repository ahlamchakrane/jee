package ma.enset.ExamenJee.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Session{
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "session")
	private List<Inspiration> inspirations= new ArrayList<>();
	@ManyToOne
	private Moderateur moderateur;
	@ManyToOne
	private Salle salle;
	@OneToMany(mappedBy = "session")
	private List<Conference> conferences= new ArrayList<>();
}

