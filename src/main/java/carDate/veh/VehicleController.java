/* ref: 210113B-BankApp/ */

package carDate.veh;

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

import carDate.emp.Employee;
import carDate.emp.Role;
import carDate.hire.Hires;
import carDate.hire.HireDao;
import carDate.hire.HireRepo;

@Controller
public class VehicleController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VehicleDao vehDao;
	@Autowired
	private HireDao hireDao;
	
	@Autowired
	private VehStatusRepo vehStatusRepo;
	
	@GetMapping("/veh")
	public String viewVehPage(Model model) {
		
		model.addAttribute("listVehicles", vehDao.getAllVehicles());
		log.info("=====> vehiclesController/viewVehPage size(): " + vehDao.getAllVehicles().size());
		return "veh/vehicles";
		
	}
	
	@GetMapping("/veh/new")
	public String showNewEmpForm(Model model) {
		Vehicle veh = new Vehicle();
		List<VehStatus> listVehStatus = vehStatusRepo.findAll();
//		model.addAttribute("listHires", hireDao.getAllHires());
		model.addAttribute("vehicle", veh);
		model.addAttribute("listVehStatus", listVehStatus);
		log.info("=====> new vehicle");
		return "veh/vehicleNew";
	}
	
	@PostMapping(value = "/veh/save")
	public String saveVeh(@Valid @ModelAttribute("vehicle") Vehicle veh, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "veh/vehicleNew";

		vehDao.save(veh);
		return "redirect:/veh";
	}
	
	@GetMapping("/vehEdit/{vehId}")
	public String showFormForUpdate(@PathVariable(value = "vehId") long vehId, Model model) {
		Vehicle vehicle = vehDao.getVehicleById(vehId);
		model.addAttribute("vehicle", vehicle);
		List<VehStatus> listVehStatus = vehStatusRepo.findAll();
		model.addAttribute("listVehStatus", listVehStatus);
		return "veh/vehicleEdit";
	}

	@GetMapping("/veh/delete/{vehId}")
	public String deleteVehicle(@PathVariable(name = "vehId") Long vehId) {
		vehDao.delete(vehId);
		log.warn("=====> delete(vehId): " + vehId);
		return "redirect:/veh";
	}
	
}
