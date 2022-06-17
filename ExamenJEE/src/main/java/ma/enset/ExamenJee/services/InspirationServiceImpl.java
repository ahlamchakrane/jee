package ma.enset.ExamenJee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ExamenJee.dtos.InspirationDTO;
import ma.enset.ExamenJee.entities.Inspiration;
import ma.enset.ExamenJee.mappers.ApplicationMappers;
import ma.enset.ExamenJee.reposirories.InspirationRepository;
@Service
@Transactional 
@Slf4j //pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class InspirationServiceImpl implements InspirationService {
	private InspirationRepository inspirationRepository;
	private ApplicationMappers applicationMappers;
	
	public InspirationServiceImpl(InspirationRepository inspirationRepository, ApplicationMappers applicationMappers) {
		this.inspirationRepository = inspirationRepository;
		this.applicationMappers = applicationMappers;
	}	

	@Override
	public InspirationDTO saveInspirationDTO(InspirationDTO inspirationDTO) {
		log.info("Saving new Inspiration");
		Inspiration inspiration = applicationMappers.fromInspirationDTO(inspirationDTO);
		Inspiration savedInspiration = inspirationRepository.save(inspiration);
		return applicationMappers.fromInspiration(savedInspiration);
	}
	@Override
	public InspirationDTO updateInspirationDTO(InspirationDTO inspirationDTO) {
		log.info("Updating Inspiration");
		Inspiration inspiration = applicationMappers.fromInspirationDTO(inspirationDTO);
		Inspiration savedInspiration = inspirationRepository.save(inspiration);
		return applicationMappers.fromInspiration(savedInspiration);
	}
	@Override
	public void deleteInspiration(Long inspirationId) {
		inspirationRepository.deleteById(inspirationId);
	}

	@Override
	public List<InspirationDTO> listInspirationsDTO() {
		List<Inspiration> inspirations = inspirationRepository.findAll();
		List<InspirationDTO> inspirationsDTO = inspirations.stream().map(inspiration -> applicationMappers.fromInspiration(inspiration)).collect(Collectors.toList());
		return inspirationsDTO;
	}
	@Override
	public InspirationDTO getInspirationDTO(Long id){
		Inspiration inspiration = inspirationRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("Inspiration not found"));
		return applicationMappers.fromInspiration(inspiration);
	}

	@Override
	public List<InspirationDTO> searchInspirations(String keyword) {
		return null;
		/*List<Inspiration> inspirations = inspirationRepository.searchInspiration(keyword);
		List<InspirationDTO> inspirationDTOs= inspirations.stream().map(cust->applicationMappers.fromInspiration(cust)).collect(Collectors.toList());
		return inspirationDTOs;*/
	}
}
