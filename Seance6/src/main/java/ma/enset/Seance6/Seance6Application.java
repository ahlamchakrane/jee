package ma.enset.Seance6;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.enset.Seance6.entities.Patient;
import ma.enset.Seance6.repositories.PatientRepository;
import ma.enset.Seance6.security.Service.SecurityService;

@SpringBootApplication
public class Seance6Application {

	public static void main(String[] args) {
		SpringApplication.run(Seance6Application.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//@Bean
	CommandLineRunner commandeLineRunner(PatientRepository patientRepository) {
		return args -> {
			patientRepository.save(new Patient(null,"ahlam",new Date(),true,30));
			patientRepository.save(new Patient(null,"aicha",new Date(),false,10));
			patientRepository.save(new Patient(null,"taoufik",new Date(),false,20));
			patientRepository.save(new Patient(null,"anas",new Date(),true,100));
			patientRepository.findAll().forEach(p->{
				System.out.println(p.getNom());
			});
		};
	}
	@Bean
	CommandLineRunner saveUsers(SecurityService securityService) {
		return args -> {
			securityService.saveUser("fatima","1234","1234");
			securityService.saveUser("hassan", "1234", "1234");
			
			securityService.saveRole("USER", "role user");
			securityService.saveRole("ADMIN", "role admin");
			
			securityService.addRoleToUser("fatima", "USER");
			securityService.addRoleToUser("fatima", "ADMIN");
			securityService.addRoleToUser("hassan", "USER");
			};
	}
		
}
