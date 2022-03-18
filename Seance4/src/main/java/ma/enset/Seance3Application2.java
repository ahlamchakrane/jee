package ma.enset;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.enset.Repositories.ConsultationRepository;
import ma.enset.Repositories.MedecinRepository;
import ma.enset.Repositories.PatientRepository;
import ma.enset.Repositories.RendezVousRepository;
import ma.enset.enteties.Consultation;
import ma.enset.enteties.Medecin;
import ma.enset.enteties.Patient;
import ma.enset.enteties.RendezVous;
import ma.enset.enteties.StatusRDV;

@SpringBootApplication
public class Seance3Application2 {

	public static void main(String[] args) {
		SpringApplication.run(Seance3Application2.class, args);
	}
	@Bean //execution au demarrage
	CommandLineRunner start(
			PatientRepository patientRepository,
			MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository,
			ConsultationRepository consultationRepository) {
		return args -> {
			//pour les patients
			Stream.of("Ahlam", "Aya").forEach(nom ->{
				Patient patient= new Patient();
				patient.setDateNaissance(new Date());
				patient.setMalade(false);
				patient.setNom(nom);
				patientRepository.save(patient);
			});
			//les medecins
			Stream.of("Aicha", "Anas").forEach(nom ->{
				Medecin medecin= new Medecin();
				medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
				medecin.setEmail(nom+"@gmail.com");
				medecin.setNom(nom);
				medecinRepository.save(medecin);
			});
			//les rendez-vous
			Stream.of("Aicha", "Anas").forEach(nom ->{
				Medecin medecin= new Medecin();
				medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
				medecin.setEmail(nom+"@gmail.com");
				medecin.setNom(nom);
				medecinRepository.save(medecin);
			});
			Patient patient=patientRepository.findById(1L).orElse(null);
			List<Patient> patients=patientRepository.findByNom("ahlam");
			List<Medecin> medecins=medecinRepository.findByNom("Anas");
			RendezVous rendezVous= new RendezVous();
			Consultation consultation = new Consultation();
			
			rendezVous.setDate(new Date());
			rendezVous.setMedecin(medecinRepository.findAll().get(0));
			rendezVous.setPatient(patientRepository.findAll().get(0));
			rendezVous.setId(UUID.randomUUID().toString());
			rendezVous.setStatus(StatusRDV.DONE);
			rendezVousRepository.save(rendezVous);
			
			//RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);
			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			consultation.setDateConsultation(new Date());
			consultation.setRapport("Rapport de consultation");
			consultation.setRendezVous(rendezVous1);
			consultationRepository.save(consultation);
			
		};
	}
}
