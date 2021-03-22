package carDate.book;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import carDate.cust.Customer;
import carDate.cust.CustomerDao;
import carDate.hire.HireDao;
import carDate.hire.Hires;
import carDate.veh.Vehicle;
import carDate.veh.VehicleDao;

@Controller
public class BookingControl {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HireDao hireDao;
	@Autowired
	private CustomerDao custDao;
	@Autowired
	private VehicleDao vehDao;

	
	@RequestMapping("booking")
	public String bookCustVeh(Model model) {
		Hires hire = new Hires();
		model.addAttribute("hire", hire);
		List<Customer> customers = custDao.getAllCustomers();
		model.addAttribute("customers", customers);
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		model.addAttribute("vehicles", vehicles);
		return "book/custVeh";
	}	
	
	@RequestMapping("/book/dates")
	public String bookDates(@ModelAttribute("hire") Hires hire, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors())
			return "book/custVeh";
		
		long bookCustId = hire.getCustomer().getCustId();
		Customer cust = custDao.getCustomerById(bookCustId);
		model.addAttribute("customers", cust);
		
		long bookVehId = hire.getVehicle().getVehId();
		Vehicle veh = vehDao.getVehicleById(bookVehId);
		model.addAttribute("vehicles", veh);
		
		Vehicle thisVeh = vehDao.getVehicleById(bookVehId);
		List<Hires> listBddates = hireDao.getAllHiresByVehicle(thisVeh);
		String[] fdates = LocalDateArrayMany.
				allListsToDMY(listBddates);
		model.addAttribute("localDateArrayMany", fdates); // finalDates can only add once
//		Stream.of(fdates).forEach(s -> System.out.println("listToDMY :: " + s));
		
		model.addAttribute("hire", hire);

		return "book/dates";
	}

	@PostMapping(value = "/book/save")
	public String saveEmp(@Valid @ModelAttribute("hire") Hires hire, BindingResult bindingResult) {

		if(bindingResult.hasErrors())
			return "book/booking";

		hireDao.save(hire);
		log.info("=====> Booking Saved, hireId: " + hire.getHireId());
		return "redirect:/booking";
	}
}