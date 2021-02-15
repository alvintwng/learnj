/* 210113B-BankApp/CustomerController.java  */
package carDate.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/emp")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmps", employeeDao.getAllEmployees());
		return "employees";
	}
}
