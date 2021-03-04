package carDate.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // for GetMapping
@Service	// defining a bean of this EmpValid
public class EmpValid  {

	@Autowired
	private EmployeeDao empDao;
	
	@GetMapping("/test")
	public String test() {
		System.out.println("=====> T E S T");
		duplicateEmail("ntuc@ntuc.com");
		duplicateEmail("very@newEmail.com");
		return "index";
	}

	/* Return True if there are such email found in database.
	 * planning to use to check duplicate email during new emp.
	 * */
	public Boolean duplicateEmail(String newEmail) {

		Employee[] empS = empDao.getAllByEmail(newEmail);
		System.out.println("=====>  duplicateEmail: " + empS.length);
		if (empS.length >= 1) {
			System.out.println("=====> YES, email is found ");
			return true; // Return to Employee New with alert
		} else {
			System.out.println("=====> FALSE, email is available."); // Return to save, and cont.
		}
		return false;
	}	

}
