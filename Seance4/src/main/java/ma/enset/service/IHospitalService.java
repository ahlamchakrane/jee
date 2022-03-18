package ma.enset.service;

import ma.enset.enteties.Consultation;
import ma.enset.enteties.Medecin;
import ma.enset.enteties.Patient;
import ma.enset.enteties.RendezVous;

public interface IHospitalService {
	Patient savePatient(Patient patient);
	Medecin saveMedecin(Medecin medecin);
	RendezVous saveRendezVous(RendezVous rendezVous);
	Consultation saveConsultation(Consultation consultation);
}
