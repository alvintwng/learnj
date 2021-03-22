package carDate.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControl {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	
	@RequestMapping("/toDoList")
	public String toDoListPage() {
		return "main/toDoList";
	}

	@RequestMapping("/about")
	public String aboutPage() {
		return "main/about";
	}

//	@RequestMapping("/testaop")
//	public String TestAop(Principal principal) {
//		return "Welcomeaop";
//	}

	@RequestMapping("/login")
	public String login() {
			return "login";
	}

	@RequestMapping("/logout-success")
	public String logout() {
			return "logout";
	}
	
	@RequestMapping("/403")
	public String errorPage() {
			System.out.println("=====> UserController /403 ");
			return "403";
	}
	
}