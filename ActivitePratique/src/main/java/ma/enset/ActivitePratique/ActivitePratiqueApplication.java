package ma.enset.ActivitePratique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.enset.ActivitePratique.entities.EnumGenre;
import ma.enset.ActivitePratique.entities.Etudiant;
import ma.enset.ActivitePratique.repository.EtudiantRepository;
import ma.enset.ActivitePratique.security.Service.SecurityService;


@SpringBootApplication
public class ActivitePratiqueApplication {
		public static void main(String[] args) {
			SpringApplication.run(ActivitePratiqueApplication.class, args);
		}
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		//@Bean
		CommandLineRunner commandeLineRunner(EtudiantRepository etudiantRepository) {
			return args -> {
				etudiantRepository.save(new Etudiant(null,"ahlam","chakrane",new Date(), "ahlamchakrane@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"aicha","azzouzi",new Date(), "aichaazzouzi@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"anass","chakrane",new Date(), "anasschakrane@gmail.com",EnumGenre.MASCULIN,true));
				etudiantRepository.save(new Etudiant(null,"ahlam1","chakrane",new Date(), "ahlamchakrane@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"aicha1","azzouzi",new Date(), "aichaazzouzi@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"anass1","chakrane",new Date(), "anasschakrane@gmail.com",EnumGenre.MASCULIN,true));
				etudiantRepository.save(new Etudiant(null,"ahlam2","chakrane",new Date(), "ahlamchakrane@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"aicha2","azzouzi",new Date(), "aichaazzouzi@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"anass2","chakrane",new Date(), "anasschakrane@gmail.com",EnumGenre.MASCULIN,true));
				etudiantRepository.save(new Etudiant(null,"ahlam3","chakrane",new Date(), "ahlamchakrane@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"aicha3","azzouzi",new Date(), "aichaazzouzi@gmail.com",EnumGenre.FEMININ,true));
				etudiantRepository.save(new Etudiant(null,"anass3","chakrane",new Date(), "anasschakrane@gmail.com",EnumGenre.MASCULIN,true));
			};
		}
		//@Bean
		CommandLineRunner saveUsers(SecurityService securityService) {
			return args -> {
				securityService.saveUser("user1","1234","1234");
				securityService.saveUser("admin1", "1234", "1234");
				
				securityService.saveRole("USER", "role user");
				securityService.saveRole("ADMIN", "role admin");
				
				securityService.addRoleToUser("user1", "USER");
				securityService.addRoleToUser("admin1", "ADMIN");
				securityService.addRoleToUser("admin1", "USER");
				};
		}
			
	}
