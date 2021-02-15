/* ref: 210113B-BankApp/ */

package carDate.veh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleController {
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@GetMapping("/veh")
	public String viewHomePage(Model model) {
		
		model.addAttribute("listVehicles", vehicleDao.getAllVehicles());
		System.out.println("***** vehicles.html");
		return "vehicles";
		
	}

}
