package ma.enset.ExamenJee.entities;

import java.util.ArrayList;
import java.util.Date;
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
public class Conference {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String heureFin;
	private String heureDebut;
	private String titre;
	@OneToMany(mappedBy = "conference")
	private List<Commentaire> commentaires= new ArrayList<>();
	@ManyToOne
	private Speaker speaker;
	@ManyToOne
	private Session session;
}