package ma.enset.ActivitePratique.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import ma.enset.ActivitePratique.entities.Etudiant;
import ma.enset.ActivitePratique.repository.EtudiantRepository;

@Controller
@AllArgsConstructor
public class EtudiantController {
	private EtudiantRepository etudiantRepository;
	@GetMapping(path = "/user/index")
	public String patients(Model model,
			@RequestParam(name= "page", defaultValue = "0") int page,
			@RequestParam(name= "size", defaultValue = "8") int size,
			@RequestParam(name= "keyword", defaultValue = "") String keyword) {
		
		Page<Etudiant> pageEtudiants = etudiantRepository.findByNomContains(keyword,PageRequest.of(page, size));
		model.addAttribute("ListEtudiants",pageEtudiants.getContent());
		model.addAttribute("pages", new int[pageEtudiants.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("keyword",keyword);
		return "etudiants";
	}
	@GetMapping("/admin/delete")
	public String delete(Long id, String keyword, int page) {
		etudiantRepository.deleteById(id);
		return "redirect:/user/index?page="+page+"&keyword="+keyword;
	}
	@GetMapping("/admin/add")
	public String add(Model model) {
		model.addAttribute("etudiant",new Etudiant());
		return "addEtudiant";
	}
	@PostMapping("/admin/save")
	public String save(Model model,@Valid Etudiant etudiant, BindingResult bindingResult,
		@RequestParam(defaultValue ="0" ) int page,
		@RequestParam(defaultValue ="" ) String keyword) {
		if(bindingResult.hasErrors()) return "addEtudiant";
		etudiantRepository.save(etudiant);
		return "redirect:/user/index?page="+page+"&keyword="+keyword;
	}
	@GetMapping("/admin/update")
	public String update(Model model, Long id, int page, String keyword) {
		Etudiant etudiant=etudiantRepository.findById(id).get();
		model.addAttribute("etudiant",etudiant);
		model.addAttribute("page",page);
		model.addAttribute("keyword",keyword);
		return "updateEtudiant";
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}
