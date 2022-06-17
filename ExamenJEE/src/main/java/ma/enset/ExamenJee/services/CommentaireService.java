package ma.enset.ExamenJee.services;

import java.util.List;

import ma.enset.ExamenJee.dtos.CommentaireDTO;


public interface CommentaireService {
		CommentaireDTO saveCommentaireDTO(CommentaireDTO commentaireDTO);
		CommentaireDTO updateCommentaireDTO(CommentaireDTO commentaireDTO);
		void deleteCommentaire(Long id);
		List<CommentaireDTO> listCommentairesDTO();
		CommentaireDTO getCommentaireDTO(Long id);
		List<CommentaireDTO> searchCommentaires(String keyword);
}
