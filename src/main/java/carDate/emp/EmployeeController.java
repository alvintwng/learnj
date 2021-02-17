/* 210113B-BankApp/CustomerController.java 
 * 210203Q-BankApp/auth/UserControl.java */
package carDate.emp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
//	@GetMapping("/emp")
//	public String viewHomePage(Model model) {
//		model.addAttribute("listEmps", employeeDao.getAllEmployees());
//		return "employees";
//	}

	@Autowired
	private RoleRepo rolerepo;
	
	@GetMapping("/emp")
	public String viewHomePage(Model model) {
		List<Role> roles = rolerepo.findAll();
		model.addAttribute("roles", roles);
		model.addAttribute("listEmps", employeeDao.getAllEmployees());
		return "employees";
	}
	
}
