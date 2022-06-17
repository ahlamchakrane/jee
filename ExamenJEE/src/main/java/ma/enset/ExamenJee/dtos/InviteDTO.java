package ma.enset.ExamenJee.dtos;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data 
public class InviteDTO extends ParticipentDTO{
	private String affiliation;
	private List<InspirationDTO> inspirationsDTO = new ArrayList<>();
}

