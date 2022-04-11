package ma.enset;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ma.enset.Repositories.PatientRepository;
import ma.enset.enteties.Patient;

@SpringBootApplication
public class Seance2Application implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Seance2Application.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		for(int i=0; i<100; i++) {
			patientRepository.save(
					new Patient(null,"ahlam",new Date(), Math.random()>0.5?true:false, 5)
					);
		}
		//obtenir que 5 => pagination
		//chager 0 par 1 pour passer à la page suivante
		Page<Patient> patients=patientRepository.findAll(PageRequest.of(0, 5));
		System.out.println("Total des pages"+patients.getTotalPages());
		System.out.println("Total des elements"+patients.getTotalElements());
		System.out.println("Numero de La page"+patients.getNumber());
	     List<Patient> byMalade= patientRepository.findByMalade(false);
	     List<Patient> chercher= patientRepository.chercherPatients2("%h%", 30);
	     Page<Patient> byMalade2= patientRepository.findByMalade(false,PageRequest.of(2, 3));
		patients.forEach(patient->{
			System.out.println(patient.getId());
			System.out.println(patient.getNom());
			System.out.println(patient.getScore());
			System.out.println(patient.getDateNaissance());
			System.err.println(patient.isMalade());
		});
		System.out.println();
		Patient patient=patientRepository.findById(1L).orElse(null);
		if(patient != null) {
			System.out.println(patient.getNom());
			System.err.println(patient.isMalade());
		}
		patient.setScore(12);
		patientRepository.save(patient);
		patientRepository.deleteById(1L);
	}
	

}
