package ma.enset.ExamenJee.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import ma.enset.ExamenJee.enumerations.Genre;

@Data 
public class ParticipentDTO {
	private Long id;
	private String type;
	private String name;
	private String email;
	private String photo;
	private Genre genre;
	private String affiliation;
	private String lien;
	private String specialité;
	private List<CommentaireDTO> commentairesDTO= new ArrayList<>();
}
