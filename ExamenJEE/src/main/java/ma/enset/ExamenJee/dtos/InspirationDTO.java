package ma.enset.ExamenJee.dtos;
import java.util.Date;

import lombok.Data;
import ma.enset.ExamenJee.enumerations.Status;
@Data 
public class InspirationDTO {
	private Long id;
	private Date date;
	private Status status;
	private String photo;
	private Double prix;
	private SessionDTO session;
	private InviteDTO invité;
}