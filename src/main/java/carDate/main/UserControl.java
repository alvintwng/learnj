package carDate.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import carDate.book.History;
import carDate.book.HistoryRepo;
import carDate.cust.Customer;
import carDate.cust.CustomerDao;
import carDate.hire.Hire;
import carDate.hire.HireDao;
import carDate.main.chartData.MthAmt;
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

	@RequestMapping("/")
	public String indexPage(Model model) {
		return "redirect:/chart/6";
	}
	
	@RequestMapping("/chart/{range}")
	public String chartPage(@PathVariable("range") Integer range, Model model) {
		
		List<Vehicle> listVeh = vehDao.getAllVehicles();
		List<Customer> listCust = custDao.getAllCustomers();
		List<History> listHist = histRepo.findAll();		
		
		////// capture the startDate of graph by index.html
		LocalDateTime startChart = LocalDateTime.now().minusYears(1); // default
		
		if (range == 6){
			startChart =  LocalDateTime.now().minusMonths(6);
		}
		if (range == 12){
			startChart =  LocalDateTime.now().minusYears(1);
		}
		if (range == 24){
			startChart =  LocalDateTime.now().minusYears(2);
		}
		log.info("=====> Chart/{range}:" + range + " startDate:" + startChart);

        ///// Vehicle usage pie chart on index.html
		Map<String, Integer> graphDataVeh = new TreeMap<>();
		for (Vehicle v : listVeh) {
			List<Hire> h = hireDao.getAllHiresByVehicle(v);
			
			graphDataVeh.put( v.getVehLicPlate() , h.size());
		}
        model.addAttribute("chartDataVeh", graphDataVeh);
        
        ///// Customer usage pie chart on index.html
        Map<String, Integer> graphDataCus = new TreeMap<>();
		for (Customer cust : listCust) {
			List<Hire> custList = hireDao.findAllByCustomer(cust);
			graphDataCus.put( cust.getCustName() , custList.size());
		}
		model.addAttribute("chartDataCus", graphDataCus);      
		
		///// History bar chart on index.html
		LocalDateTime afterDate = startChart;
		LocalDateTime beforeDate = LocalDateTime.now();
		
		List<History> histList = histRepo.findAll();
		List<MthAmt> mthAmtList = new ArrayList<>();
		mthAmtList = chartData.ChartData(histList,afterDate,beforeDate);
		
		Map<String, Integer> graphDataBar = new TreeMap<>();
		for (MthAmt m : mthAmtList) {
			graphDataBar.put(m.getMthEnum(), m.gettAmount() );
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
	
	@RequestMapping("/test")
	public String toDoList(Model model) {
		//System.out.println("Test ");
		log.info("=====> toDoList ");	
		return "main/toDoList";
	}
}