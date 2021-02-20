/* 210113B-BankApp/CustomerController.java  * 210203Q-BankApp/auth/UserControl.java 
 * 210127N-BankApp/.../CustomerController.java * 210208B-SpringManyToMany */
package carDate.emp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepo empRepo;

	@Autowired
	private RoleRepo rolerepo;

	@GetMapping("/emp")
	public String viewEmpPage(Model model) {
		List<Employee> listEmps = empRepo.findAll();
		model.addAttribute("listEmps", listEmps);
		return "employees";
	}
	
	@GetMapping("/emp/new")
	public String showNewEmpForm(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		List<Role> roles = rolerepo.findAll();
		model.addAttribute("roles", roles);
		return "employeeNew";
	}
	
	@GetMapping("/emp/edit/{empId}")
	public ModelAndView editEmployee(@PathVariable(value = "empId") long empId) {
		ModelAndView mav = new ModelAndView("employeeEdit");	
		Employee emp = empRepo.findById(empId).get();
		mav.addObject("employee", emp);		
		List<Role> roles = rolerepo.findAll();
		mav.addObject("roles", roles);
		return mav;
	}
	
//	@GetMapping("/emp/edit/{empId}")
//	public String editEmployee(@PathVariable("empId") Long empId, Model model) {
//		Employee emp = employeeDao.getEmployeeById(empId);
//		model.addAttribute("employee", emp);
//		List<Role> roles = rolerepo.findAll();
//		model.addAttribute("roles", roles);
//		return "empEdit";
//	}
	
//	@RequestMapping(value = "/emp/save", method = RequestMethod.POST) // was
	@PostMapping(value = "/emp/save")
	public String saveEmp(@ModelAttribute("employee") Employee emp) {
		System.out.println(" ====> EmpController /save ");
		empRepo.save(emp);
		return "redirect:/emp";
	}

	@GetMapping("/emp/delete/{empId}")
	public String deleteEmplopyee(@PathVariable(name = "empId") Long empId) {
		System.out.println("=====> EmpController /delete ");
		empRepo.deleteById(empId);
		return "redirect:/emp";
	}
	
}
