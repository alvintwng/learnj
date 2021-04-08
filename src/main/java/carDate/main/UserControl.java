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
	
	@RequestMapping("/")
	public String indexPage(Model model) {

		List<Vehicle> listVeh = vehDao.getAllVehicles();

        Map<String, Integer> graphData = new TreeMap<>();
		for (Vehicle v : listVeh) {
			List<Hire> h = hireDao.getAllHiresByVehicle(v);
			
			graphData.put( v.getVehLicPlate() , h.size());
		}

        model.addAttribute("chartData", graphData);
        
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