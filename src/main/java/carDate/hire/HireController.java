package carDate.hire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HireController {
	
	@Autowired
	private HireDao hireDao;
	
	@GetMapping("/hire")
	public String viewHomePage(Model model) {
		
		model.addAttribute("listHires", hireDao.getAllHires());
		return "hires";
		
	}
}
