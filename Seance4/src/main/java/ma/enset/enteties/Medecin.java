package ma.enset.enteties;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Medecin {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String email;
	private String specialite;
	@OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
	@JsonProperty(access= JsonProperty.Access.READ_ONLY)
	private Collection<RendezVous> rendezVous;
	
}
