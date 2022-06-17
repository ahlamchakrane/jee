package ma.enset.ExamenJee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ExamenJee.dtos.CommentaireDTO;
import ma.enset.ExamenJee.entities.Commentaire;
import ma.enset.ExamenJee.mappers.ApplicationMappers;
import ma.enset.ExamenJee.reposirories.CommentaireRepository;
@Service
@Transactional 
@Slf4j //pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class CommentaireServiceImpl implements CommentaireService {
	private CommentaireRepository commentaireRepository;
	private ApplicationMappers applicationMappers;
	
	public CommentaireServiceImpl(CommentaireRepository commentaireRepository, ApplicationMappers applicationMappers) {
		this.commentaireRepository = commentaireRepository;
		this.applicationMappers = applicationMappers;
	}	

	@Override
	public CommentaireDTO saveCommentaireDTO(CommentaireDTO commentaireDTO) {
		log.info("Saving new Commentaire");
		Commentaire commentaire = applicationMappers.fromCommentaireDTO(commentaireDTO);
		Commentaire savedCommentaire = commentaireRepository.save(commentaire);
		return applicationMappers.fromCommentaire(savedCommentaire);
	}
	@Override
	public CommentaireDTO updateCommentaireDTO(CommentaireDTO commentaireDTO) {
		log.info("Updating Commentaire");
		Commentaire commentaire = applicationMappers.fromCommentaireDTO(commentaireDTO);
		Commentaire savedCommentaire = commentaireRepository.save(commentaire);
		return applicationMappers.fromCommentaire(savedCommentaire);
	}
	@Override
	public void deleteCommentaire(Long commentaireId) {
		commentaireRepository.deleteById(commentaireId);
	}

	@Override
	public List<CommentaireDTO> listCommentairesDTO() {
		List<Commentaire> commentaires = commentaireRepository.findAll();
		List<CommentaireDTO> commentairesDTO = commentaires.stream().map(commentaire -> applicationMappers.fromCommentaire(commentaire)).collect(Collectors.toList());
		return commentairesDTO;
	}
	@Override
	public CommentaireDTO getCommentaireDTO(Long id){
		Commentaire commentaire = commentaireRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("Commentaire not found"));
		return applicationMappers.fromCommentaire(commentaire);
	}

	@Override
	public List<CommentaireDTO> searchCommentaires(String keyword) {
		return null;
		/*List<Commentaire> commentaires = commentaireRepository.searchCommentaire(keyword);
		List<CommentaireDTO> commentaireDTOs= commentaires.stream().map(cust->applicationMappers.fromCommentaire(cust)).collect(Collectors.toList());
		return commentaireDTOs;*/
	}
}
