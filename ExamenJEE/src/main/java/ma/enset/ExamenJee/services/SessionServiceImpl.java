package ma.enset.ExamenJee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ExamenJee.dtos.SessionDTO;
import ma.enset.ExamenJee.entities.Session;
import ma.enset.ExamenJee.mappers.ApplicationMappers;
import ma.enset.ExamenJee.reposirories.SessionRepository;

@Service
@Transactional 
@Slf4j //pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class SessionServiceImpl implements SessionService {
	private SessionRepository sessionRepository;
	private ApplicationMappers applicationMappers;
	
	public SessionServiceImpl(SessionRepository sessionRepository, ApplicationMappers applicationMappers) {
		this.sessionRepository = sessionRepository;
		this.applicationMappers = applicationMappers;
	}	

	@Override
	public SessionDTO saveSessionDTO(SessionDTO sessionDTO) {
		log.info("Saving new Session");
		Session session = applicationMappers.fromSessionDTO(sessionDTO);
		Session savedSession = sessionRepository.save(session);
		return applicationMappers.fromSession(savedSession);
	}
	@Override
	public SessionDTO updateSessionDTO(SessionDTO sessionDTO) {
		log.info("Updating Session");
		Session session = applicationMappers.fromSessionDTO(sessionDTO);
		Session savedSession = sessionRepository.save(session);
		return applicationMappers.fromSession(savedSession);
	}
	@Override
	public void deleteSession(Long id) {
		sessionRepository.deleteById(id);
	}

	@Override
	public List<SessionDTO> listSessionsDTO() {
		List<Session> sessions = sessionRepository.findAll();
		List<SessionDTO> sessionsDTO = sessions.stream().map(session -> applicationMappers.fromSession(session)).collect(Collectors.toList());
		return sessionsDTO;
	}
	@Override
	public SessionDTO getSessionDTO(Long id){
		Session session = sessionRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("Session not found"));
		return applicationMappers.fromSession(session);
	}

	@Override
	public List<SessionDTO> searchSessions(String keyword) {
		return null;
		/*List<Session> sessions = sessionRepository.searchSession(keyword);
		List<SessionDTO> sessionDTOs= sessions.stream().map(cust->applicationMappers.fromSession(cust)).collect(Collectors.toList());
		return sessionDTOs;*/
	}
}
