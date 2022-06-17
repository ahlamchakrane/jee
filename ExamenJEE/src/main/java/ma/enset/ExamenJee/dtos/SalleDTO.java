package ma.enset.ExamenJee.dtos;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data 
public class SalleDTO{
	private Long id;
	private String name;
	private List<SessionDTO> sesionsDTO= new ArrayList<>();
}
