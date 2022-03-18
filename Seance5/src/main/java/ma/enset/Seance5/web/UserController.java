package ma.enset.Seance5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ma.enset.Seance5.entities.User;
import ma.enset.Seance5.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/users/{username}")
	public User user(@PathVariable String username) {
		User user = userService.findUserByUsername(username);
		return user;
	}
}
