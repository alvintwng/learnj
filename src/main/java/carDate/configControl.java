package carDate;

//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.validation.Valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import carDate.emp.Employee;
//import carDate.emp.Role;
//import carDate.emp.RoleDao;
//import carDate.emp.RoleRepo;
//
//@Controller
//public class configControl {
//
//	private final Logger log = LoggerFactory.getLogger(getClass());
//
//	RoleDao roleDao;
//	RoleRepo roleRepo;
//
//	@RequestMapping("/testconfig")
//	public String configToFactorySet(Model model) {
//
//		Role ro = new Role();
//		model.addAttribute("role", ro);
//
//		System.out.println("=====> configToFactorySet() creating new role ");
//		return "emp/roleNew";
//	}
///*
// 	@GetMapping("/emp/new")
//	public String showNewEmpForm(Model model) {
//		Employee emp = new Employee();
//		model.addAttribute("employee", emp);
//		List<Role> roles = rolerepo.findAll();
//		model.addAttribute("roles", roles);
//		log.info("=====> new ");
//		return "emp/employeeNew";
//	}
// * */
//	@PostMapping(value = "/roleSave")
//	public String saveEmp(@Valid @ModelAttribute("role") Role ro, BindingResult bindingResult) {
//		
//		if(bindingResult.hasErrors())
//			return "emp/roleNew";
//
//		System.out.println("=====> configToFactorySet() saving new role ");
////		roleDao.save(ro);
////		roleRepo.save(ro);
//
//		log.info("=====> configToFactorySet() new role: " + ro.getName());
//		return "redirect:/";
//	}
//	
//	@RequestMapping("/config")
//	public void testDirectAddRole(Model model) {
////		Role roleSuper = new Role("SUPER");
////		Role rolesMonkey = new Role("MONKEY");
////		Role rolesHello = new Role("HELLO");
////		EntityManager.persist(roleSuper);
////		roleRepo.
//		
//	}
//}

/*
 public Hires(Customer customer, Vehicle vehicle, @NotNull LocalDate dateStart, @NotNull LocalDate dateEnd,
			boolean casedone) 
 * */

 