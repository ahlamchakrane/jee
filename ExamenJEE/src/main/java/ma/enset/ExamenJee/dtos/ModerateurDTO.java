package ma.enset.ExamenJee.dtos;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data 
public class ModerateurDTO extends ParticipentDTO{
	private String specialit√©;
	private List<SessionDTO> sessionsDTO = new ArrayList<>();
}


