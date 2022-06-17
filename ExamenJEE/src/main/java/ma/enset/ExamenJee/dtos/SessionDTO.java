package ma.enset.ExamenJee.dtos;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data 
public class SessionDTO{
	private Long id;
	private String name;
	private List<InspirationDTO> inspirationsDTO= new ArrayList<>();
	private ModerateurDTO moderateurDTO;
	private SalleDTO salleDTO;
}

