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

@Controller
public class VehicleController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VehicleDao vehDao;
	
	@Autowired
	private VehStatusRepo vehStatusRepo;

	
	@GetMapping("/veh")
	public String viewVehPage(Model model) {
		
		model.addAttribute("listVehicles", vehDao.getAllVehicles());
		
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
		return "veh/vehicleEdit";
	}
	
	@PostMapping(value = "/veh/save")
	public String saveVeh(@Valid @ModelAttribute("vehicle") Vehicle veh, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			log.warn("=====> saveVeh, bindingResult.getAllErrors():: " 
					+ bindingResult.getAllErrors());
			return "veh/vehicleNew";
		}

		vehDao.save(veh);
		log.info("=====> Saved vehicle");
		return "redirect:/veh";
	}
	
	@GetMapping("/vehEdit/{vehId}")
	public String showFormForUpdate(@PathVariable(value = "vehId") long vehId, Model model) {
		Vehicle vehicle = vehDao.getVehicleById(vehId);
		model.addAttribute("vehicle", vehicle);
		List<VehStatus> listVehStatus = vehStatusRepo.findAll();
		model.addAttribute("listVehStatus", listVehStatus);
		
		log.info("=====> vehEdit, vehId: " + vehicle.getVehId());
		return "veh/vehicleEdit";
	}

	@GetMapping("/veh/delete/{vehId}")
	public String deleteVehicle(@PathVariable(name = "vehId") long vehId, Model model) {
		try {
			vehDao.delete(vehId);
			log.warn("=====> delete(vehId): " + vehId);
		} catch (Exception e ) {
			log.warn("=====> delete(vehId) => Something went wrong to: " + vehId);
            log.error(e.toString());
            model.addAttribute("error", ("Unable to delete!\n" + e.toString()));
            return "error";
		}

		return "redirect:/veh";
	}

}
