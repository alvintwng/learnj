/* 210113B-BankApp/CustomerController.java  * 210203Q-BankApp/auth/UserControl.java 
 * 210127N-BankApp/.../CustomerController.java * 210208B-SpringManyToMany 
 * 20210221*/
package carDate.emp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@Controller
public class EmployeeController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RoleRepo rolerepo;

	@GetMapping("/emp")
	public String viewEmpPage(Model model) {
		List<Role> roles = rolerepo.findAll();
		model.addAttribute("roles", roles);
		model.addAttribute("listEmps", employeeDao.getAllEmployees());
		return "employees";
	}

	@GetMapping("/emp/prf/{empId}")
	public ModelAndView profileEmployee(@PathVariable(value = "empId") long empId) {
		ModelAndView mav = new ModelAndView("employeeProfile");	
		Employee emp = employeeDao.getEmployeeById(empId);
		mav.addObject("employee", emp);		
		List<Role> roles = rolerepo.findAll();
		mav.addObject("roles", roles);
		log.info("=====> employeeProfile/{empId}: " + emp.getEmpId());
		return mav;
	}
	
	@GetMapping("/emp/new")
	public String showNewEmpForm(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		List<Role> roles = rolerepo.findAll();
		model.addAttribute("roles", roles);
		log.info("=====> new ");
		return "employeeNew";
	}
	
	@GetMapping("/emp/edit/{empId}")
	public ModelAndView editEmployee(@PathVariable(value = "empId") long empId) {
		ModelAndView mav = new ModelAndView("employeeEdit");	
		Employee emp = employeeDao.getEmployeeById(empId);
		mav.addObject("employee", emp);		
		List<Role> roles = rolerepo.findAll();
		mav.addObject("roles", roles);
		log.info("=====> edit/{empId}: " + emp.getEmpId());
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
	
	@PostMapping(value = "/emp/save")
	public String saveEmp(@Valid @ModelAttribute("employee") Employee emp, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "employeeNew";

		employeeDao.save(emp);
		log.info("=====> save: " + emp.getEmpName());
		return "redirect:/emp";
	}

	@GetMapping("/emp/delete/{empId}")
	public String deleteEmplopyee(@PathVariable(name = "empId") Long empId) {
		employeeDao.delete(empId);
		log.warn("=====> delete(empId): " + empId);
		return "redirect:/emp";
	}
	
}
