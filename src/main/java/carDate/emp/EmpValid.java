package carDate.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//ntucLH/mFCapStoneProj5/210108-2ShoppingCart/
@Controller
public class EmpValid  {
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@GetMapping("/test")
	public String test() {
		System.out.println("=====> T E S T");
		duplicateEmail("ntuc@ntuc.com");
//		duplicateEmail("very@newEmail.com");
		return "test";
	}

	/*
	 * Return True if there are such email found in database.
	 * */
	// planning to use to check duplicate email during new emp.
//	@GetMapping("/emp/test")
	public Boolean duplicateEmail(String newEmail) {
/*
		String newEmail = emp.getEmail();
		Employee[] empS = empRepo.findAllByEmail(newEmail);
		System.out.println("=====> empS.length;" + empS.length);
 */
		Employee[] empS = empRepo.findAllByEmail(newEmail);
		System.out.println("=====>  duplicateEmail: " + empS.length);
		if (empS.length >= 1) {
			System.out.println("=====> YES, email is found ");
			return true;
			// Return to Employee New with alert
		} else {
			System.out.println("=====> FALSE, email is available.");
			// Return to save, and cont.
		}
		return false;
	}	
	
//	// planning to use to check duplicate email during new emp.
////	@GetMapping("/emp/test")
//	public String betaDuplicateEmail(String newEmail) {
//
//		Employee emp = new Employee();
//		emp = empDao.findByEmail(newEmail);
//		if (emp.emailIsValid("root@carDate.com")) {
//			System.out.println("=====> YES, email is valid");
//		} else {
//			System.out.println("=====> FALSE, email is InValid");
//		};
//		System.out.println("=====> " + emp.getEmpName());
//		return "test";
//	}

}
