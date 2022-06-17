package ma.enset.ExamenJee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.enset.ExamenJee.dtos.CommentaireDTO;
import ma.enset.ExamenJee.dtos.ParticipentDTO;
import ma.enset.ExamenJee.dtos.SalleDTO;
import ma.enset.ExamenJee.dtos.SessionDTO;
import ma.enset.ExamenJee.enumerations.Genre;
import ma.enset.ExamenJee.services.CommentaireService;
import ma.enset.ExamenJee.services.ConferenceService;
import ma.enset.ExamenJee.services.InspirationService;
import ma.enset.ExamenJee.services.ParticipentService;
import ma.enset.ExamenJee.services.SalleService;
import ma.enset.ExamenJee.services.SessionService;

@SpringBootApplication
public class ExamenJeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenJeeApplication.class, args);
	}
	@Bean
	public CommandLineRunner start(CommentaireService commentaireService, SalleService salleService,  ConferenceService conferenceService, SessionService sessionService,
									InspirationService inspirationService,
									ParticipentService participentService) {
		return args -> {
			/*
			Stream.of("Hassan","imane","Mohamed").forEach(name->{
				ParticipentDTO participentDTO = new ParticipentDTO();
				participentDTO.setEmail(name+"@gmail.com");
				participentDTO.setName(name);
				participentDTO.setType(Math.random() > 0.5 ? "Moderateur": "Speaker");
				participentDTO.setGenre(Math.random() > 0.5 ? Genre.FEMININ : Genre.MASCULIN);
				participentService.saveParticipentDTO(participentDTO);
			});
			Stream.of("Session1","Session2","Session3").forEach(name->{
				SessionDTO sessionDTO = new SessionDTO();
				sessionDTO.setName(name);
				sessionService.saveSessionDTO(sessionDTO);
			});
			
			sessionService.listSessionsDTO().forEach(cast->{
				try {
					Stream.of("Salle info","Salle mathematique","Salle conference").forEach(name->{
						SalleDTO salleDTO = new SalleDTO();
						salleDTO.setName(name);
						salleService.saveSalleDTO(salleDTO);
						cast.setSalleDTO(salleDTO);
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			
			participentService.listParticipentsDTO().forEach(pat->{
				try {
					List<CommentaireDTO> list = new ArrayList<>();
					CommentaireDTO commentaire = new CommentaireDTO();
					commentaire.setContenue("Contenu");
					commentaire.setDate(new Date());
					commentaire.setNbrLikes(Math.random()*1000);
					commentaireService.saveCommentaireDTO(commentaire);	
					list.add(commentaire);
					pat.setCommentairesDTO(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});*/
		};
	}
}
