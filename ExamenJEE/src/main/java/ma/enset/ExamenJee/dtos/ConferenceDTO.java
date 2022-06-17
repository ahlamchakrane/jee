package ma.enset.ExamenJee.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
@Data
public class ConferenceDTO {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String heureFin;
	private String heureDebut;
	private String titre;
	private List<CommentaireDTO> commentairesDTO= new ArrayList<>();
	private SpeakerDTO speakerDTO;
	private SessionDTO sessionDTO;
}