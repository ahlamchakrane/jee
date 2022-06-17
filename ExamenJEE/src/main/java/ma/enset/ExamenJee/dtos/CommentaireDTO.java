package ma.enset.ExamenJee.dtos;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data 
public class CommentaireDTO {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String contenue;
	private Double nbrLikes;
}
