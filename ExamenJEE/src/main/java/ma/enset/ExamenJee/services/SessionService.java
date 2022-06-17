package ma.enset.ExamenJee.services;

import java.util.List;

import ma.enset.ExamenJee.dtos.SessionDTO;


public interface SessionService {
		SessionDTO saveSessionDTO(SessionDTO sessionDTO);
		SessionDTO updateSessionDTO(SessionDTO sessionDTO);
		void deleteSession(Long id);
		List<SessionDTO> listSessionsDTO();
		SessionDTO getSessionDTO(Long id);
		List<SessionDTO> searchSessions(String keyword);
}
