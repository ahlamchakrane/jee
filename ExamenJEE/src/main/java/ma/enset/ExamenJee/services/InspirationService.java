package ma.enset.ExamenJee.services;

import java.util.List;

import ma.enset.ExamenJee.dtos.InspirationDTO;


public interface InspirationService {
		InspirationDTO saveInspirationDTO(InspirationDTO inspirationDTO);
		InspirationDTO updateInspirationDTO(InspirationDTO inspirationDTO);
		void deleteInspiration(Long id);
		List<InspirationDTO> listInspirationsDTO();
		InspirationDTO getInspirationDTO(Long id);
		List<InspirationDTO> searchInspirations(String keyword);
}
