package ma.enset.ExamenJee.services;

import java.util.List;

import ma.enset.ExamenJee.dtos.SalleDTO;


public interface SalleService {
		SalleDTO saveSalleDTO(SalleDTO salleDTO);
		SalleDTO updateSalleDTO(SalleDTO salleDTO);
		void deleteSalle(Long id);
		List<SalleDTO> listSallesDTO();
		SalleDTO getSalleDTO(Long id);
		List<SalleDTO> searchSalles(String keyword);
}
