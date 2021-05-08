/* 210113B-BankApp/CustomerController.java */

package carDate.cust;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CustomerController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustStateRepo stateRepo;
	
	@GetMapping("/cust")
	public String viewCustomerPage(Model model) {
		model.addAttribute("listCustomers", customerDao.getAllCustomers());
		return "cust/customer";
	}

	@GetMapping("/cust/new")
	public String showNewCustForm(Model model) {
		Customer cust = new Customer();
		model.addAttribute("customer", cust);
		List<CustState> listCustStates = stateRepo.findAll();
		model.addAttribute("listCustStates", listCustStates);
		
		log.info("=====> customerNew ");
		return "cust/customerEdit";
	}	
	
	@GetMapping("/cust/prf/{custId}")
	public String profileCustomer(@PathVariable("custId") Long custId, Model model) {
		Customer cust = customerDao.getCustomerById(custId);
		model.addAttribute("customer", cust);
		List<CustState> listCustStates = stateRepo.findAll();
		model.addAttribute("listCustStates", listCustStates);
		
		return "cust/customerProfile";
	}
	
	@GetMapping("/cust/edit/{custId}")
	public String editCustomer(@PathVariable("custId") Long custId, Model model) {
		Customer cust = customerDao.getCustomerById(custId);
		model.addAttribute("customer", cust);
		List<CustState> listCustStates = stateRepo.findAll();
		model.addAttribute("listCustStates", listCustStates);
		
		log.info("=====> editCustomer, custId: " + cust.getCustId());
		return "cust/customerEdit";
	}
	
	@PostMapping(value = "/cust/save")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer cust, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			log.warn("=====> saveCustomer, bindingResult.getAllErrors():: " 
						+ bindingResult.getAllErrors());
			return "cust/customerNew";
		}

		customerDao.save(cust);
		
		log.info("=====> saveCustomer, custId: " + cust.getCustId());
		return "redirect:/cust";
	}

 	@GetMapping("/cust/delete/{custId}")
	public String deleteCustomer(Model model, @PathVariable(name = "custId") Long custId) {
		try {
			customerDao.delete(custId);
			log.warn("=====> delete(custId): " + custId);
		} catch (Exception e) {
            log.warn("=====> delete(custId) => Something went wrong to: " + custId);
            log.error(e.toString());
            model.addAttribute("error", "Unable to delete!\n" + e.toString());
            return "error";
            //https://github.com/alvintwng/ntucLH/tree/master/m9JavaSe2
		}
		
		return "redirect:/cust";
	}

}

 