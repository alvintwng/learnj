package carDate.book;

import java.time.LocalDateTime;
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
	@Autowired
	private DailyRateRepo rateRepo;
	@Autowired
	private HistoryRepo historyRepo;
	
	@RequestMapping("booking")
	public String booking(Model model) {
		return "redirect:/bookCust";
	}

	@RequestMapping("bookCust")
	public String bookCust(Model model) {
		Booking booking = new Booking();
		model.addAttribute("booking", booking);
		List<Customer> customers = custDao.getAllCustomers();
		model.addAttribute("customers", customers);
		return "book/bookCust";
	}
	
	// this method not working yet ... from HireController ...
	@RequestMapping("bookVeh/{custId}")
	public String bookVehCustomer(@PathVariable("custId") Long custId, Model model) {
		System.out.println("=====> bookVeh/{custId}: " + custId);
		//return "redirect:/bookCust";
		return "book/bookVeh";
	}
	
	@PostMapping(value = "bookVeh")
	public String bookVeh(@ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			log.warn("=====> bookVeh, BindingResult: ERROR");
			return "bookVeh";
		}

		model.addAttribute("booking", booking);

		Customer customer = booking.getCustomer();
		model.addAttribute("customer", customer);
		
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		model.addAttribute("vehicles", vehicles);
		
		model.addAttribute("listHires", hireDao.findAllByCustomer(customer));
		
		log.info("=====> bookVeh, custid: " + customer.getCustId());
		return "book/bookVeh";
	}	
	

//	// skip
//	//@PostMapping(value = "/book/dates")
//	public String bookDates(@ModelAttribute("hire") Hires hire, BindingResult bindingResult, Model model) {
//		if(bindingResult.hasErrors())
//			return "book/custVeh";
//		
//		long bookCustId = hire.getCustomer().getCustId();
//		Customer cust = custDao.getCustomerById(bookCustId);
//		model.addAttribute("customers", cust);
//		
//		long bookVehId = hire.getVehicle().getVehId();
//		Vehicle veh = vehDao.getVehicleById(bookVehId);
//		model.addAttribute("vehicles", veh);
//		
//		model.addAttribute("dayRate", calDayRateByHire(hire));
//		
//		Vehicle thisVeh = vehDao.getVehicleById(bookVehId);
//		List<Hires> listBddates = hireDao.getAllHiresByVehicle(thisVeh);
//		String[] fdates = LocalDateArrayMany.
//				allListsToDMY(listBddates);
//		model.addAttribute("localDateArrayMany", fdates);
//		//Stream.of(fdates).forEach(s -> System.out.println("listToDMY :: " + s));
//		
//		model.addAttribute("hire", hire);
//		return "book/bookDate";
//	}
	
	@PostMapping(value = "/book/calDate")
	public String bookCalDate(@ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "book/bookVeh";
		
		model.addAttribute("booking", booking);
		
		Customer customer = booking.getCustomer();
		model.addAttribute("customer", customer);
		
		Vehicle vehicle = booking.getVehicle();
		model.addAttribute("vehicle", vehicle);
		
		model.addAttribute("dayRate", calDayRateByBooking(booking));
		
		List<Hires> listBddates = hireDao.getAllHiresByVehicle(vehicle);
		String[] fdates = LocalDateArrayMany.allListsToDMY(listBddates);
		model.addAttribute("localDateArrayMany", fdates);
		
		//System.out.println("=====> calDate}: booking : " + booking.toString() );
		return "book/bookCalDate";
	}
	
	@PostMapping(value = "/book/cnfm")
	public String bookConfirm(@ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "book/bookVeh";
		
		model.addAttribute("dayRate", calDayRateByBooking(booking));
		
		//System.out.println("=====> book/cnfm}: " + booking.toString());
		return "book/bookConfirm";
	}
	
	@PostMapping(value = "/book/save")
	public String saveBooking(@Valid @ModelAttribute("hire") Hires hire, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			log.warn("=====> book/save, bindingResult.getAllErrors():: " 
					+ bindingResult.getAllErrors());
			return "/403";
		}

		hireDao.save(hire);
		//System.out.println("=====> book/save.. hire: " + hire.toString());
		
		long newHireId = hire.getHireId();
		log.warn("=====> Booking Saved, hireId: " + newHireId);
		
		Hires latestHire = hireDao.getHireById(newHireId);
		History history = 
		hireToHistory(latestHire);
		historyRepo.save(history);
		log.info("=====> History Saved. id:" + history.getId());
	
		return "redirect:/";
	}

	@GetMapping("/hist")
	public String historyList(Model model) {
		List<History> histories = historyRepo.findAll();
		model.addAttribute("histories", histories);
		List<Customer> customers = custDao.getAllCustomers();
		model.addAttribute("customers", customers);
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		model.addAttribute("vehicles", vehicles);
		//System.out.println("====> History Size: " + histories.size());
		return "book/historyList";
	}
	
	@GetMapping("/histData")
	public String historyDate(Model model) {
		List<History> histories = historyRepo.findAll();
		model.addAttribute("histories", histories);
		return "book/historyData";
	}
	
	public History hireToHistory(Hires hire) {
		History history = new History();
		history.setHireId(		hire.getHireId());
		history.setCustId(		hire.getCustomer().getCustId());
		history.setVehId(		hire.getVehicle().getVehId());
		history.setDateStart(	hire.getDateStart());
		history.setDateEnd(		hire.getDateEnd());
		history.setCustNric(	hire.getCustomer().getNric());
		history.setCustCatId(	hire.getCustomer().getCustState().getCustStateId());
		history.setVehClassId(	hire.getVehicle().getVehStatus().getVehSttsId());
		history.setVehLicPlate(	hire.getVehicle().getVehLicPlate());
		history.setDayrate(		calDayRateByHire(hire)); // by Hire
		history.setAmoint(		calAmount(hire));
		history.setRecorded(	LocalDateTime.now());
		
		//System.out.println("====> hireToHistory history: " + history.toString());
		return history;
	}

	
	public float calDayRateByBooking(Booking booking) {
		long vehStatId = booking.getVehicle().getVehStatus().getVehSttsId();
		DailyRate dR = rateRepo.findByVehClassId(vehStatId);
		float dayRate = (float)dR.getDayrate();
		return dayRate;
	}
	
	public float calDayRateByHire(Hires hire) {
		long vehStatId = hire.getVehicle().getVehStatus().getVehSttsId();
		DailyRate dR = rateRepo.findByVehClassId(vehStatId);
		float dayRate = (float)dR.getDayrate();
		return dayRate;
	}
	
	public float calAmount(Hires hire) {
		int getDays = LocalDateArrayMany.getDays(hire.getDateStart(), hire.getDateEnd().plusDays(1));	
		float dayRate = calDayRateByHire(hire);
		float amount = dayRate * getDays;
		return amount;
	}
	
}