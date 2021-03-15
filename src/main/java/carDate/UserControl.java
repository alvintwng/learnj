/* ref: 210126-DaoAuthenticate/UserControl.java */
package carDate;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControl {
	
	
	@RequestMapping("/toDoList")
	public String toDoListPage() {
		return "toDoList";
	}
	
	@RequestMapping("/about")
	public String aboutPage() {
		return "about";
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