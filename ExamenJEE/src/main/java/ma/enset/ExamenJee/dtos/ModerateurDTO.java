package ma.enset.ExamenJee.dtos;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data 
public class ModerateurDTO extends ParticipentDTO{
	private String specialité;
	private List<SessionDTO> sessionsDTO = new ArrayList<>();
}


