package carDate.main;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import carDate.book.History;
import carDate.book.HistoryRepo;
import carDate.cust.Customer;
import carDate.cust.CustomerDao;
import carDate.hire.Hire;
import carDate.hire.HireDao;
import carDate.veh.Vehicle;
import carDate.veh.VehicleDao;

@Controller
public class UserControl {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private HireDao hireDao;
	@Autowired
	private VehicleDao vehDao;
	@Autowired
	private CustomerDao custDao;
	@Autowired
	private HistoryRepo histRepo;

	@RequestMapping("/test")
	public String toDoList(Model model) {
		// data from history, cluster to months, output array of mothn with total amount 
		//System.out.println("Test ");
		log.info("=====> toDoList ");	
		return "main/toDoList";
	}
	
	@RequestMapping("/")
	public String indexPage(Model model) {

		List<Vehicle> listVeh = vehDao.getAllVehicles();

        Map<String, Integer> graphDataVeh = new TreeMap<>();
		for (Vehicle v : listVeh) {
			List<Hire> h = hireDao.getAllHiresByVehicle(v);
			
			graphDataVeh.put( v.getVehLicPlate() , h.size());
		}
        model.addAttribute("chartDataVeh", graphDataVeh);
        
        
        List<Customer> listCust = custDao.getAllCustomers();
        Map<String, Integer> graphDataCus = new TreeMap<>();
		for (Customer cust : listCust) {
			List<Hire> custList = hireDao.findAllByCustomer(cust);
			graphDataCus.put( cust.getCustName() , custList.size());
		}
		model.addAttribute("chartDataCus", graphDataCus);      
		
		List<History> listHist = histRepo.findAll();
		Map<String, Integer> graphDataBar = new TreeMap<>();
		for (History hist : listHist) {
			graphDataBar.put( (""+ hist.getHireId() ) , (int)hist.getAmoint());
			//System.out.println("History " + hist.getHireId() + " : " + hist.getAmoint());
		}
		model.addAttribute("chartDataBar", graphDataBar);
		
		return "index";
	}

	@RequestMapping("/toDoList")
	public String toDoListPage() {
		return "main/toDoList";
	}
	
	@RequestMapping("/about")
	public String aboutPage() {
		return "main/about";
	}

//	@RequestMapping("/testaop")
//	public String TestAop(Principal principal) {
//		return "Welcomeaop";
//	}

	@RequestMapping("/login")
	public String login() {
		log.info("=====> login ");	
		return "login";
	}

	@RequestMapping("/logout-success")
	public String logout() {
		log.info("=====> logout ");	
		return "logout";
	}
	
	@RequestMapping("/403")
	public String errorPage() {
		log.warn("=====>  UserController /403 ");	
		return "403";
	}
	
}