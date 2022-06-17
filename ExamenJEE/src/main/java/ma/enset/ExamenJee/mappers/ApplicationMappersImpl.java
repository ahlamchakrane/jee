package ma.enset.ExamenJee.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
@Service
public class ApplicationMappersImpl implements ApplicationMappers {

	@Override
	public Commentaire fromCommentaireDTO(CommentaireDTO commentaireDTO) {
		Commentaire commentaire = new Commentaire();
		BeanUtils.copyProperties(commentaireDTO, commentaire);
		return commentaire;
		
	}

	@Override
	public List<CommentaireDTO> fromCommentaires(List<Commentaire> commentaires) {
		List<CommentaireDTO> commentairesDTO = new  ArrayList<CommentaireDTO>();
		BeanUtils.copyProperties(commentaires, commentairesDTO);
		return commentairesDTO;
	}
	
	@Override
	public List<Commentaire> fromCommentairesDTO(List<CommentaireDTO> commentairesDTO) {
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		BeanUtils.copyProperties(commentairesDTO, commentaires);
		return commentaires;
		
	}

	@Override
	public CommentaireDTO fromCommentaire(Commentaire commentaire) {
		CommentaireDTO commentaireDTO = new CommentaireDTO();
		BeanUtils.copyProperties(commentaire, commentaireDTO);
		return commentaireDTO;
	}

	@Override
	public Conference fromConferenceDTO(ConferenceDTO conferenceDTO) {
		Conference conference = new Conference();
		BeanUtils.copyProperties(conferenceDTO, conference);
		return conference;
	}

	@Override
	public ConferenceDTO fromConference(Conference conference) {
		ConferenceDTO conferenceDTO = new ConferenceDTO();
		BeanUtils.copyProperties(conference, conferenceDTO);
		return conferenceDTO;
	}

	@Override
	public Inspiration fromInspirationDTO(InspirationDTO inspirationDTO) {
		Inspiration inspiration = new Inspiration();
		BeanUtils.copyProperties(inspirationDTO, inspiration);
		return inspiration;
	}

	@Override
	public InspirationDTO fromInspiration(Inspiration inspiration) {
		InspirationDTO inspirationDTO = new InspirationDTO();
		BeanUtils.copyProperties(inspiration, inspirationDTO);
		return inspirationDTO;
	}

	@Override
	public Participent fromParticipentDTO(ParticipentDTO participentDTO) {
		Participent participent= new Participent();
		BeanUtils.copyProperties(participentDTO, participent);
		return participent;
	}

	@Override
	public ParticipentDTO fromParticipent(Participent participent) {
		ParticipentDTO participentDTO = new ParticipentDTO();
		BeanUtils.copyProperties(participent, participentDTO);
		return participentDTO;
	}

	@Override
	public Salle fromSalleDTO(SalleDTO salleDTO) {
		Salle salle = new Salle();
		BeanUtils.copyProperties(salleDTO, salle);
		return salle;
	}

	@Override
	public SalleDTO fromSalle(Salle salle) {
		SalleDTO salleDTO = new SalleDTO();
		BeanUtils.copyProperties(salle, salleDTO);
		return salleDTO;
	}

	@Override
	public Session fromSessionDTO(SessionDTO sessionDTO) {
		Session session = new Session();
		BeanUtils.copyProperties(sessionDTO, session);
		return session;
	}

	@Override
	public SessionDTO fromSession(Session session) {
		SessionDTO sessionDTO = new SessionDTO();
		BeanUtils.copyProperties(session, sessionDTO);
		return sessionDTO;
	}

	@Override
	public Speaker fromSpeakerDTO(SpeakerDTO speakerDTO) {
		Speaker speaker = new Speaker();
		BeanUtils.copyProperties(speakerDTO, speaker);
		return speaker;
	}

	@Override
	public SpeakerDTO fromSpeaker(Speaker speaker) {
		SpeakerDTO speakerDTO = new SpeakerDTO();
		BeanUtils.copyProperties(speaker, speakerDTO);
		return speakerDTO;
	}

	@Override
	public Moderateur fromModerateurDTO(ModerateurDTO moderateurDTO) {
		Moderateur moderateur = new Moderateur();
		BeanUtils.copyProperties(moderateurDTO, moderateur);
		return moderateur;
	}

	@Override
	public ModerateurDTO fromModerateur(Moderateur moderateur) {
		ModerateurDTO moderateurDTO = new ModerateurDTO();
		BeanUtils.copyProperties(moderateur, moderateurDTO);
		return moderateurDTO;
	}

	@Override
	public Invite fromInviteDTO(InviteDTO inviteDTO) {
		Invite  invite = new Invite();
		BeanUtils.copyProperties(inviteDTO, invite);
		return invite;
	}

	@Override
	public InviteDTO fromInvite(Invite invite) {
		InviteDTO  inviteDTO = new  InviteDTO();
		BeanUtils.copyProperties(invite, inviteDTO);
		return inviteDTO;
	}
	
}
