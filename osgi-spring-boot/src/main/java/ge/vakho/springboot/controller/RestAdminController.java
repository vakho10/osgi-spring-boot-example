package ge.vakho.springboot.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAdminController {

	@Secured("ROLE_USER")
	@GetMapping("/admin")
	public String action(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return "Secured page accessed :)" //
				+ "Your username is: " + userDetails.getUsername() //
				+ ", and you roles are: "
				+ userDetails.getAuthorities().parallelStream().map(a -> a.getAuthority()).reduce("", String::concat);
	}

}