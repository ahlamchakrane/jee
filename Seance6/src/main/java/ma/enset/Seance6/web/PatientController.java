package ma.enset.Seance6.web;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import ma.enset.Seance6.entities.Patient;
import ma.enset.Seance6.repositories.PatientRepository;

@Controller
@AllArgsConstructor
public class PatientController {
	private PatientRepository patientRepository;
	@GetMapping(path = "/index")
	public String patients(Model model,
			@RequestParam(name= "page", defaultValue = "0") int page,
			@RequestParam(name= "size", defaultValue = "8") int size,
			@RequestParam(name= "keyword", defaultValue = "") String keyword) {
		
		Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
		model.addAttribute("ListPatients",pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("keyword",keyword);
		return "patients";
	}
	@GetMapping("/delete")
	public String delete(Long id, String keyword, int page) {
		patientRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
	@GetMapping("/add")
	public String add() {
		return "addPatient";
	}
	@PostMapping("/save")
	public String save(Model model, Patient patient) {
		
		patientRepository.save(patient);
		return "redirect:/index";
	}
	@GetMapping("/update")
	public String update(Model model, Long id) {
		Patient patient=patientRepository.findById(id).get();
		model.addAttribute("patient",patient);
		return "updatePatient";
	}
	
	@GetMapping("/")
	public String home() {
		return "redirect:/index";
	}
	/**/
}
