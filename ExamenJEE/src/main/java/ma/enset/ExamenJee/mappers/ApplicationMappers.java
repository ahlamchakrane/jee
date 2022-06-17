package ma.enset.ExamenJee.mappers;

import java.util.List;

import ma.enset.ExamenJee.dtos.CommentaireDTO;
import ma.enset.ExamenJee.dtos.ConferenceDTO;
import ma.enset.ExamenJee.dtos.InspirationDTO;
import ma.enset.ExamenJee.dtos.InviteDTO;
import ma.enset.ExamenJee.dtos.ModerateurDTO;
import ma.enset.ExamenJee.dtos.ParticipentDTO;
import ma.enset.ExamenJee.dtos.SalleDTO;
import ma.enset.ExamenJee.dtos.SessionDTO;
import ma.enset.ExamenJee.dtos.SpeakerDTO;
import ma.enset.ExamenJee.entities.Commentaire;
import ma.enset.ExamenJee.entities.Conference;
import ma.enset.ExamenJee.entities.Inspiration;
import ma.enset.ExamenJee.entities.Invite;
import ma.enset.ExamenJee.entities.Moderateur;
import ma.enset.ExamenJee.entities.Participent;
import ma.enset.ExamenJee.entities.Salle;
import ma.enset.ExamenJee.entities.Session;
import ma.enset.ExamenJee.entities.Speaker;

public interface ApplicationMappers {
	//commentaire
	Commentaire fromCommentaireDTO(CommentaireDTO commentaireDTO);
	CommentaireDTO fromCommentaire(Commentaire commentaire);
	//liste des commantaire
	List<Commentaire> fromCommentairesDTO(List<CommentaireDTO> commentaireDTO);
	List<CommentaireDTO> fromCommentaires(List<Commentaire> commentaire);
	//conference
	Conference fromConferenceDTO(ConferenceDTO conferenceDTO);
	ConferenceDTO fromConference(Conference conference);
	//inspiration
	Inspiration fromInspirationDTO(InspirationDTO inspirationDTO);
	InspirationDTO fromInspiration(Inspiration inspiration);
	//Participent
	Participent fromParticipentDTO(ParticipentDTO participentDTO);
	ParticipentDTO fromParticipent(Participent participent);
	//Salle
	Salle fromSalleDTO(SalleDTO salleDTO);
	SalleDTO fromSalle(Salle salle);
	//Session
	Session fromSessionDTO(SessionDTO sessionDTO);
	SessionDTO fromSession(Session session);
	//Speaker
	Speaker fromSpeakerDTO(SpeakerDTO speakerDTO);
	SpeakerDTO fromSpeaker(Speaker speaker);
	//Moderateur
	Moderateur fromModerateurDTO(ModerateurDTO moderateurDTO);
	ModerateurDTO fromModerateur(Moderateur moderateur);
	//Invite
	Invite fromInviteDTO(InviteDTO inviterDTO);
	InviteDTO fromInvite(Invite invite);
}
