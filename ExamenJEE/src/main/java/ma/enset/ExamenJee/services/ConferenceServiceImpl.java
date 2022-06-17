package ma.enset.ExamenJee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ExamenJee.dtos.ConferenceDTO;
import ma.enset.ExamenJee.entities.Conference;
import ma.enset.ExamenJee.mappers.ApplicationMappers;
import ma.enset.ExamenJee.reposirories.ConferenceRepository;
@Service
@Transactional 
@Slf4j //pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class ConferenceServiceImpl implements ConferenceService {
	private ConferenceRepository conferenceRepository;
	private ApplicationMappers applicationMappers;
	
	public ConferenceServiceImpl(ConferenceRepository conferenceRepository, ApplicationMappers applicationMappers) {
		this.conferenceRepository = conferenceRepository;
		this.applicationMappers = applicationMappers;
	}	

	@Override
	public ConferenceDTO saveConferenceDTO(ConferenceDTO conferenceDTO) {
		log.info("Saving new Conference");
		Conference conference = applicationMappers.fromConferenceDTO(conferenceDTO);
		Conference savedConference = conferenceRepository.save(conference);
		return applicationMappers.fromConference(savedConference);
	}
	@Override
	public ConferenceDTO updateConferenceDTO(ConferenceDTO conferenceDTO) {
		log.info("Updating Conference");
		Conference conference = applicationMappers.fromConferenceDTO(conferenceDTO);
		Conference savedConference = conferenceRepository.save(conference);
		return applicationMappers.fromConference(savedConference);
	}
	@Override
	public void deleteConference(Long conferenceId) {
		conferenceRepository.deleteById(conferenceId);
	}

	@Override
	public List<ConferenceDTO> listConferencesDTO() {
		List<Conference> conferences = conferenceRepository.findAll();
		List<ConferenceDTO> conferencesDTO = conferences.stream().map(conference -> applicationMappers.fromConference(conference)).collect(Collectors.toList());
		return conferencesDTO;
	}
	@Override
	public ConferenceDTO getConferenceDTO(Long id){
		Conference conference = conferenceRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("Conference not found"));
		return applicationMappers.fromConference(conference);
	}

	@Override
	public List<ConferenceDTO> searchConferences(String keyword) {
		return null;
		/*List<Conference> conferences = conferenceRepository.searchConference(keyword);
		List<ConferenceDTO> conferenceDTOs= conferences.stream().map(cust->applicationMappers.fromConference(cust)).collect(Collectors.toList());
		return conferenceDTOs;*/
	}
}
