package carDate.book;

import java.time.LocalDate;
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
import carDate.hire.Hire;
import carDate.hire.HireDao;
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
		List<Customer> customers = custDao.getAllCustomers();
		
		model.addAttribute("booking", booking);
		model.addAttribute("customers", customers);
		return "book/bookCust"; // post > bookVeh
	}
	
	// this method not working yet ... from HireController ...
	@RequestMapping("bookVeh/{custId}")
	public String bookVehCustomer(@PathVariable("custId") Long custId, Model model) {
		System.out.println("=====> bookVeh/{custId}: " + custId);
		//return "redirect:/bookCust";
		return "book/bookVeh"; // post > book/calDate
	}
	
	@PostMapping(value = "bookVeh")
	public String bookVeh(@ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			log.warn("=====> bookVeh, BindingResult: ERROR");
			return "bookVeh";
		}

		Customer customer = booking.getCustomer();
		List<Vehicle> vehicles = vehDao.getAllVehicles();	
		
		model.addAttribute("booking", booking);
		model.addAttribute("customer", customer);
		model.addAttribute("vehicles", vehicles);
		model.addAttribute("listHires", hireDao.findAllByCustomer(customer));
		log.info("=====> bookVeh, custid: " + customer.getCustId());
		return "book/bookVeh"; // post > book/calDate
	}	
	
	@PostMapping(value = "/book/calDate")
	public String bookCalDate(@ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "book/bookVeh";

		Customer customer = booking.getCustomer();
		Vehicle vehicle = booking.getVehicle();	
		
		List<Hire> listBddates = hireDao.getAllHiresByVehicle(vehicle);
		String[] fdates = LocalDateArrayMany.allListsToDMY(listBddates);	
		
		model.addAttribute("booking", booking);
		model.addAttribute("customer", customer);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("dayRate", calDayRateByBooking(booking));
		model.addAttribute("localDateArrayMany", fdates);
		log.info("=====> book/calDate, fdates");
		return "book/bookCalDate";	// post > book/cnfm 
	}
	
	@PostMapping(value = "/book/cnfm")
	public String bookConfirm(@ModelAttribute("booking") Booking booking, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "book/bookVeh";

		Boolean chkvalid = LocalDateArrayMany.checkValidDates(booking.getDateStart(), booking.getDateEnd());
		Boolean crashed = crashedDates(booking.getVehicle() ,booking.getDateStart(), booking.getDateEnd());
		
		// for return to bookCalDate
		List<Hire> listBddates = hireDao.getAllHiresByVehicle(booking.getVehicle());
		String[] fdates = LocalDateArrayMany.allListsToDMY(listBddates);
		
		model.addAttribute("dayRate", calDayRateByBooking(booking));
		model.addAttribute("booking", booking);
		model.addAttribute("customer", booking.getCustomer());
		model.addAttribute("vehicle", booking.getVehicle());
		model.addAttribute("localDateArrayMany", fdates);
		
		if(!chkvalid)
			return "book/bookCalDate";	

		if(crashed) 
			return "book/bookCalDate";	
		
		log.info("=====> book/cnfm, " + chkvalid + !crashed );
		return "book/bookConfirm";	// Post > book/save	
	}

	
	@PostMapping(value = "/book/save")
	public String saveBooking(@Valid @ModelAttribute("hire") Hire hire, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			log.warn("=====> book/save, bindingResult.getAllErrors():: " 
					+ bindingResult.getAllErrors());
			return "/403";
		}

		hireDao.save(hire);
		
		long newHireId = hire.getHireId();
		log.warn("=====> Booking Saved, hireId: " + newHireId);
		
		Hire latestHire = hireDao.getHireById(newHireId);
		History history = 
		hireToHistory(latestHire);
		historyRepo.save(history);
		log.info("=====> History Saved. id:" + history.getId());
	
		return "redirect:/hire";
	}

	@GetMapping("/hist")
	public String historyList(Model model) {
		List<History> histories = historyRepo.findAll();
		List<Customer> customers = custDao.getAllCustomers();
		List<Vehicle> vehicles = vehDao.getAllVehicles();
		
		model.addAttribute("histories", histories);
		model.addAttribute("customers", customers);
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
	
	@GetMapping("/histEdit/{id}")
	public String historyEdit(@PathVariable(value = "id") long id, Model model) {
		History hist = historyRepo.findById(id).get();
		model.addAttribute("hist", hist);
		return "book/historyEdit";
	}
	@PostMapping("/histSave")
	public String saveHist(History hist) {
		historyRepo.save(hist);
		log.info("=====> History Saved. id:" + hist.getId() + " " + hist.getRecorded() );
		return "redirect:/histEdit/" + hist.getId() ;
	}


	public History hireToHistory(Hire hire) {
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

		return history;
	}

	public boolean crashedDates(Vehicle veh, LocalDate newStart, LocalDate newEnd) {	
		List<Hire> listHires =  hireDao.getAllHiresByVehicle(veh);

		for (Hire b: listHires) {
			Boolean crashHireStartTrue 	=  LocalDateArrayMany.chkCrashdDates( b.getDateStart(), newStart,newEnd );
			Boolean crashHireEndTrue 	=  LocalDateArrayMany.chkCrashdDates( b.getDateEnd(), newStart,newEnd );
			Boolean crashNewInsideTrue 	=  LocalDateArrayMany.chkCrashdDates(newStart, b.getDateStart(), b.getDateEnd() );

			if ((crashHireStartTrue) || (crashHireEndTrue) || (crashNewInsideTrue) ){
				return true; }
		}
		return false;
	}
	
	public float calDayRateByBooking(Booking booking) {
		long vehStatId = booking.getVehicle().getVehStatus().getVehSttsId();
		DailyRate dR = rateRepo.findByVehClassId(vehStatId);
		float dayRate = (float)dR.getDayrate();
		return dayRate;
	}
	
	public float calDayRateByHire(Hire hire) {
		long vehStatId = hire.getVehicle().getVehStatus().getVehSttsId();
		DailyRate dR = rateRepo.findByVehClassId(vehStatId);
		float dayRate = (float)dR.getDayrate();
		return dayRate;
	}
	
	public float calAmount(Hire hire) {
		int getDays = LocalDateArrayMany.getDays(hire.getDateStart(), hire.getDateEnd().plusDays(1));	
		float dayRate = calDayRateByHire(hire);
		float amount = dayRate * getDays;
		return amount;
	}

}