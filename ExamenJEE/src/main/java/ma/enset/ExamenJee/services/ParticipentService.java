package ma.enset.ExamenJee.services;

import java.util.List;

import ma.enset.ExamenJee.dtos.ParticipentDTO;


public interface ParticipentService {
		ParticipentDTO saveParticipentDTO(ParticipentDTO participentDTO);
		ParticipentDTO updateParticipentDTO(ParticipentDTO participentDTO);
		void deleteParticipent(Long id);
		List<ParticipentDTO> listParticipentsDTO();
		ParticipentDTO getParticipentDTO(Long id);
		List<ParticipentDTO> searchParticipents(String keyword);
}
