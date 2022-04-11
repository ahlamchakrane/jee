package ma.enset.enteties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
	@Id @Column(length=50)
	private String id;
	private Date date;
	@Enumerated(EnumType.STRING) //pour qu'on trouve done ou pending.. sinon on trouve des chiffres
	private StatusRDV status;
	@ManyToOne
	@JsonProperty(access= JsonProperty.Access.READ_ONLY)
	private Patient patient;
	@ManyToOne
	private Medecin medecin;
	@OneToOne(mappedBy = "rendezVous", fetch = FetchType.LAZY)
	private Consultation consultation;
	
}
