package in.co.jk.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home","/"})
public class WelcomeCtl {

	@GetMapping
	public String display() {
		return "welcome";
	}
	@GetMapping("/aboutus")
	public String aboutus() {
		return "about";
	}
	@GetMapping("/support")
	public String support() {
		return "support";
	}
	
	
	
}
