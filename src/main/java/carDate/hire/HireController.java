package carDate.hire;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

import carDate.cust.Customer;
import carDate.cust.CustomerDao;
import carDate.inv.InvoiceDao;
import carDate.veh.Vehicle;
import carDate.veh.VehicleDao;

@Controller
public class HireController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HireDao hireDao;
	@Autowired
	private CustomerDao custDao;	
	@Autowired
	private VehicleDao vehDao;
	@Autowired
	private InvoiceDao invDao;
	
	@GetMapping("/hire")
	public String viewHirePage(Model model) {

		List<Hire> listHires = hireDao.getAllHires();
		model.addAttribute("listHires", listHires);

		//Stream.of(listHires).forEach(s -> System.out.println("hire :: " + s));
		return "hire/hires";
	}
	
/*	@GetMapping("/hire/new")
	public String showNewHireForm(Model model) {
		Hire hire = new Hire();
		model.addAttribute("hire", hire);
		List<Customer> customers = custDao.getAllCustomers();
		model.addAttribute("customers", customers);
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		model.addAttribute("vehicles", vehicles);
		
		log.info("=====> HireNew ");
		return "hire/hireEdit";
	} */

	@GetMapping("/hireEdit/{hireId}")
	public String editHireForm(@PathVariable(value = "hireId") long hireId, Model model) {
		Hire hire = hireDao.getHireById(hireId);
//		Optional<Hires> hire = hireRepo.findById(hireId);
		
		model.addAttribute("hire", hire);
		List<Customer> customers = custDao.getAllCustomers();

		model.addAttribute("customers", customers);
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		
		model.addAttribute("vehicles", vehicles);
		
		log.info("=====> hireEdit, hireId: " + hire.getHireId());
		return "hire/hireEdit";

	}	
	
	@PostMapping(value = "/hire/save")
	public String saveEmp(@Valid @ModelAttribute("hire") Hire hire, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "hire/hireNew";

		hireDao.save(hire);
		log.info("=====> saveHire, hireId: " + hire.getHireId());
		return "redirect:/hire";
	}
	
	@GetMapping("/hire/delete/{hireId}")
	public String deleteHire(@PathVariable(name = "hireId") Long hireId) {
		hireDao.delete(hireId);
		log.warn("=====> delete(hireId): " + hireId);
		return "redirect:/hire";
	}
}
