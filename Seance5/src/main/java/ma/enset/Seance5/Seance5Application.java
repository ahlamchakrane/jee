package ma.enset.Seance5;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.enset.Seance5.entities.Role;
import ma.enset.Seance5.entities.User;
import ma.enset.Seance5.service.RoleService;
import ma.enset.Seance5.service.UserService;

@SpringBootApplication
public class Seance5Application {

	public static void main(String[] args) {
		SpringApplication.run(Seance5Application.class, args);
	}
	
	@Bean
	CommandLineRunner start(UserService userService, RoleService roleService) {
		return args -> {
			User user= new User();
			user.setUsername("Ahlam");
			user.setPassword("12345");
			userService.addUser(user);
			
			User user2= new User();
			user2.setUsername("Ahlam2");
			user2.setPassword("ER12345");
			userService.addUser(user2);
			
			Stream.of("STUDENT","USER","ADMIN").forEach(r->{
				Role role1= new Role();
				role1.setDesc("role "+r);
				role1.setRoleName(r);
				roleService.addRole(role1);
			});
			userService.addRoleToUser("Ahlam","STUDENT");
			userService.addRoleToUser("Ahlam","USER");
			userService.addRoleToUser("Ahlam","ADMIN");
			userService.addRoleToUser("Ahlam2","STUDENT");
			
			try {
				User u= userService.authenticate("Ahlam","12345");
				System.out.println(u.getId());
				System.out.println(u.getUsername());
				u.getRoles().forEach(r->{
					System.out.println("Role=> "+r.getRoleName());
				});
				
			} catch(Exception e) {
				System.err.println("Ooops");
			}
			
		};
	}
}
