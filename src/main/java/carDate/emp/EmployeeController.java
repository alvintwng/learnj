/* 210113B-BankApp/CustomerController.java 
 * 210203Q-BankApp/auth/UserControl.java 
 * 210127N-BankApp/.../CustomerController.java*/
package carDate.emp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RoleRepo rolerepo;
	
	@GetMapping("/emp")
	public String viewHomePage(Model model) {
		List<Role> roles = rolerepo.findAll();
		model.addAttribute("roles", roles);
		model.addAttribute("listEmps", employeeDao.getAllEmployees());
		return "employees";
	}

	@RequestMapping("/empNew")
	public String showNewEmpForm(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		
		return "employeeNew";
	}
	
//	@GetMapping("/empEdit/{empId}")
//	public String showFormForUpdate(@PathVariable(value = "empId") long empId, Model model) {
//		// Get Employee from the Service 
//		Employee employee = employeeDao.getEmployeeById(empId);
//		
//		// set employee as a model attribute to pre-populate the form 
//		model.addAttribute("employee", employee);
//		return "empEdit";
//	}
	
	@RequestMapping("/empEdit/{empId}")
	public ModelAndView editEmployee(@PathVariable(value = "empId") long empId) {

		ModelAndView mav = new ModelAndView("empEdit");
		
		Employee emp = employeeDao.getEmployeeById(empId);
		mav.addObject("employee", emp);

		return mav;
	}
	
	@RequestMapping(value = "/empSave", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("employee") Employee emp) {
		System.out.println(" ====> EmpController /save ");
		employeeDao.save(emp);
		
		return "redirect:/";
	}

	@RequestMapping("/empDelete/{empId}")
	public String deleteEmplopyee(@PathVariable(name = "empId") Long empId) {
		System.out.println("=====> EmpController /delete ");
		employeeDao.delete(empId);
		
		return "redirect:/";
	}
	
}
