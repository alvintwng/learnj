package carDate.hire;

import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("/hire")
	public String viewHirePage(Model model) {
		List<Customer> customers = custDao.getAllCustomers();
		model.addAttribute("customers", customers);
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		model.addAttribute("vehicles", vehicles);
		model.addAttribute("listHires", hireDao.getAllHires());
		model.addAttribute("test", "test123");
		return "hire/hires";
	}
	
	@GetMapping("/hire/new")
	public String showNewHireForm(Model model) {
		Hires hire = new Hires();
		model.addAttribute("hire", hire);
		List<Customer> customers = custDao.getAllCustomers();
		model.addAttribute("customers", customers);
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		model.addAttribute("vehicles", vehicles);
		log.info("=====> HireNew ");
		return "hire/hireNew";
	}

	@GetMapping("/hireEdit/{hireId}")
	public String editHireForm(@PathVariable(value = "hireId") long hireId, Model model) {
		Hires hire = hireDao.getHireById(hireId);
//		Optional<Hires> hire = hireRepo.findById(hireId);
		
		model.addAttribute("hire", hire);
		List<Customer> customers = custDao.getAllCustomers();

		model.addAttribute("customers", customers);
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		
		model.addAttribute("vehicles", vehicles);
		return "hire/hireEdit";
	}	
	
	@PostMapping(value = "/hire/save")
	public String saveEmp(@Valid @ModelAttribute("hire") Hires hire, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "hire/hireNew";

		log.info("=====> saveHire, hireId: " + hire.getHireId());
		hireDao.save(hire);
		return "redirect:/hire";
	}
	
	@GetMapping("/hire/delete/{hireId}")
	public String deleteHire(@PathVariable(name = "hireId") Long hireId) {
		hireDao.delete(hireId);
		log.warn("=====> delete(hireId): " + hireId);
		return "redirect:/hire";
	}
}
