package ma.enset.ExamenJee.services;

import java.util.List;

import ma.enset.ExamenJee.dtos.ConferenceDTO;



public interface ConferenceService {
		ConferenceDTO saveConferenceDTO(ConferenceDTO conferenceDTO);
		ConferenceDTO updateConferenceDTO(ConferenceDTO conferenceDTO);
		void deleteConference(Long id);
		List<ConferenceDTO> listConferencesDTO();
		ConferenceDTO getConferenceDTO(Long id);
		List<ConferenceDTO> searchConferences(String keyword);
}
