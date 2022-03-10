package ma.enset.enteties;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Consultation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateConsultation;
	private String rapport;
	@OneToOne
	@JsonProperty(access= JsonProperty.Access.READ_ONLY)
	private RendezVous rendezVous;
	
}
