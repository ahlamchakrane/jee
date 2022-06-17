package ma.enset.ExamenJee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ExamenJee.dtos.ParticipentDTO;
import ma.enset.ExamenJee.entities.Invite;
import ma.enset.ExamenJee.entities.Moderateur;
import ma.enset.ExamenJee.entities.Participent;
import ma.enset.ExamenJee.entities.Speaker;
import ma.enset.ExamenJee.mappers.ApplicationMappers;
import ma.enset.ExamenJee.reposirories.ParticipentRepository;
@Service
@Transactional 
@Slf4j //pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class ParticipentServiceImpl implements ParticipentService {
	private ParticipentRepository participentRepository;
	private ApplicationMappers applicationMappers;
	private ParticipentService participentService;
	
	public ParticipentServiceImpl(ParticipentRepository participentRepository, ApplicationMappers applicationMappers) {
		this.participentRepository = participentRepository;
		this.applicationMappers = applicationMappers;
	}	

	@Override
	public ParticipentDTO saveParticipentDTO(ParticipentDTO participentDTO) {
		log.info("Saving new Participent");
		Participent participent = applicationMappers.fromParticipentDTO(participentDTO);
		Participent savedParticipent = participentRepository.save(participent);
		return applicationMappers.fromParticipent(savedParticipent);
	}
	@Override
	public ParticipentDTO updateParticipentDTO(ParticipentDTO participentDTO) {
		if(participentDTO.getType().equals("Moderateur")) {
			Moderateur moderateur = new Moderateur();
			BeanUtils.copyProperties(participentDTO, moderateur,"specialité");
			moderateur.setCommentaires(applicationMappers.fromCommentairesDTO(participentDTO.getCommentairesDTO()));
			applicationMappers.fromModerateur(moderateur);
			participentRepository.save(moderateur);
		} else if(participentDTO.getType().equals("Invite")) {
			Invite invite = new Invite();
			BeanUtils.copyProperties(participentDTO, invite,"affiliation");
			invite.setAffiliation(participentDTO.getAffiliation());
			invite.setCommentaires(applicationMappers.fromCommentairesDTO(participentDTO.getCommentairesDTO()));
			applicationMappers.fromInvite(invite);
			participentRepository.save(invite);
		} else if(participentDTO.getType().equals("Speaker")) {
			Speaker speaker = new Speaker();
			BeanUtils.copyProperties(participentDTO, speaker,"lien");
			speaker.setLien(participentDTO.getLien());
			speaker.setCommentaires(applicationMappers.fromCommentairesDTO(participentDTO.getCommentairesDTO()));
			applicationMappers.fromSpeaker(speaker);
			participentRepository.save(speaker);
		}
		return participentDTO;
	}
	
	
	@Override
	public void deleteParticipent(Long participentId) {
		participentRepository.deleteById(participentId);
	}
	
	@Override
	public List<ParticipentDTO> listParticipentsDTO() {
		List<Participent> participents = participentRepository.findAll();
		List<ParticipentDTO> participentsDTO = participents.stream().map(participent ->{
			if(participent instanceof Moderateur) {
				Moderateur moderateur = (Moderateur) participent;
				return applicationMappers.fromModerateur(moderateur);
			} else if(participent instanceof Invite) {
				Invite invite = (Invite) participent;
				return applicationMappers.fromInvite(invite);
			} else if (participent instanceof Speaker) {
				Speaker speaker = (Speaker) participent;
				return applicationMappers.fromSpeaker(speaker);
			}
			return null;
		}).collect(Collectors.toList());
	return participentsDTO;
	}
	
	@Override
	public ParticipentDTO getParticipentDTO(Long id){
		Participent participent = participentRepository.findById(id)
				.orElseThrow(() ->new RuntimeException("Participent not found"));
		if(participent instanceof Moderateur) {
			Moderateur moderateur = (Moderateur) participent;
			return applicationMappers.fromModerateur(moderateur);
		} else if(participent instanceof Invite) {
			Invite invite = (Invite) participent;
			return applicationMappers.fromInvite(invite);
		} else if (participent instanceof Speaker) {
			Speaker speaker = (Speaker) participent;
			return applicationMappers.fromSpeaker(speaker);
		}
		return null;
	}

	@Override
	public List<ParticipentDTO> searchParticipents(String keyword) {
		return null;
		/*List<Participent> participents = participentRepository.searchParticipent(keyword);
		List<ParticipentDTO> participentDTOs= participents.stream().map(cust->applicationMappers.fromParticipent(cust)).collect(Collectors.toList());
		return participentDTOs;*/
	}
}
