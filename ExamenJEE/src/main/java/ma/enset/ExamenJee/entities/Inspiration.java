package ma.enset.ExamenJee.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ExamenJee.enumerations.Status;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Inspiration {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private Status status;
	private String photo;
	private Double prix;
	@ManyToOne
	private Session session;
	@ManyToOne
	private Invite invite;
}