package ma.enset.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.enset.Repositories.PatientRepository;
import ma.enset.enteties.Patient;

@RestController
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	@GetMapping("/patients")
	public List<Patient> patientList(){
		return patientRepository.findAll();
	}
	
}
