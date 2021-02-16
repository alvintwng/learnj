/* 210113B-BankApp/CustomerController.java */

package carDate.cust;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/cust")
	public String viewHomePage(Model model) {
		
		model.addAttribute("listCustomers", customerDao.getAllCustomers());
		return "cust";
	}

}
