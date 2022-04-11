package ma.enset.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ma.enset.Repositories.ConsultationRepository;
import ma.enset.Repositories.MedecinRepository;
import ma.enset.Repositories.PatientRepository;
import ma.enset.Repositories.RendezVousRepository;
import ma.enset.enteties.Consultation;
import ma.enset.enteties.Medecin;
import ma.enset.enteties.Patient;
import ma.enset.enteties.RendezVous;
@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService{
	private PatientRepository patientRepository;
	private MedecinRepository medecinRepository;
	private RendezVousRepository rendezVousRepository;
	private ConsultationRepository consultationRepository;
	
	public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
		this.patientRepository = patientRepository;
		this.medecinRepository = medecinRepository;
		this.rendezVousRepository = rendezVousRepository;
		this.consultationRepository = consultationRepository;
	}

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Medecin saveMedecin(Medecin medecin) {
		return medecinRepository.save(medecin);
	}

	@Override
	public RendezVous saveRendezVous(RendezVous rendezVous) {
		rendezVous.setId(UUID.randomUUID().toString());
		return rendezVousRepository.save(rendezVous);
	}

	@Override
	public Consultation saveConsultation(Consultation consultation) {
		return consultationRepository.save(consultation);
	}

}
