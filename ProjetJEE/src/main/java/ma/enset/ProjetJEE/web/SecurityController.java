package ma.enset.ProjetJEE.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	@GetMapping("/404")
	public String notAuthorized() {
		return "403";
	}
}
