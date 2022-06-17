package ma.enset.ExamenJee.dtos;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SpeakerDTO extends ParticipentDTO{
	private String lien;
	private List<ConferenceDTO> conferencesDTO = new ArrayList<>();
}

