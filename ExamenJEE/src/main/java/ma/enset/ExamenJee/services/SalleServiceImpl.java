package ma.enset.ExamenJee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ExamenJee.dtos.SalleDTO;
import ma.enset.ExamenJee.entities.Salle;
import ma.enset.ExamenJee.mappers.ApplicationMappers;
import ma.enset.ExamenJee.reposirories.SalleRepository;
@Service
@Transactional 
@Slf4j //pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class SalleServiceImpl implements SalleService {
	private SalleRepository salleRepository;
	private ApplicationMappers applicationMappers;
	
	public SalleServiceImpl(SalleRepository salleRepository, ApplicationMappers applicationMappers) {
		this.salleRepository = salleRepository;
		this.applicationMappers = applicationMappers;
	}	

	@Override
	public SalleDTO saveSalleDTO(SalleDTO salleDTO) {
		log.info("Saving new Salle");
		Salle salle = applicationMappers.fromSalleDTO(salleDTO);
		Salle savedSalle = salleRepository.save(salle);
		return applicationMappers.fromSalle(savedSalle);
	}
	@Override
	public SalleDTO updateSalleDTO(SalleDTO salleDTO) {
		log.info("Updating Salle");
		Salle salle = applicationMappers.fromSalleDTO(salleDTO);
		Salle savedSalle = salleRepository.save(salle);
		return applicationMappers.fromSalle(savedSalle);
	}
	@Override
	public void deleteSalle(Long salleId) {
		salleRepository.deleteById(salleId);
	}

	@Override
	public List<SalleDTO> listSallesDTO() {
		List<Salle> salles = salleRepository.findAll();
		List<SalleDTO> sallesDTO = salles.stream().map(salle -> applicationMappers.fromSalle(salle)).collect(Collectors.toList());
		return sallesDTO;
	}
	@Override
	public SalleDTO getSalleDTO(Long id){
		Salle salle = salleRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("Salle not found"));
		return applicationMappers.fromSalle(salle);
	}

	@Override
	public List<SalleDTO> searchSalles(String keyword) {
		return null;
		/*List<Salle> salles = salleRepository.searchSalle(keyword);
		List<SalleDTO> salleDTOs= salles.stream().map(cust->applicationMappers.fromSalle(cust)).collect(Collectors.toList());
		return salleDTOs;*/
	}
}
