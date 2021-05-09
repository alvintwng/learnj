package carDate.main;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
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

import carDate.book.DailyRate;
import carDate.book.DailyRateRepo;
import carDate.cust.CustState;
import carDate.cust.CustStateRepo;
import carDate.emp.Role;
import carDate.emp.RoleRepo;
import carDate.veh.VehStatus;
import carDate.veh.VehStatusRepo;
import carDate.veh.VehicleRepo;


@Controller
public class configControl {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustStateRepo custStateRepo;
	@Autowired
	private VehStatusRepo vehStatusRepo;
	@Autowired
	private DailyRateRepo dailyRateRepo;
	@Autowired
	private RoleRepo rolerepo;
	
	@GetMapping("/main/config")
	public String viewConfig(Model model) {
		
		List<CustState> listCustStates = custStateRepo.findAll();
		model.addAttribute("listCustStates", listCustStates);
		
		List<VehStatus> listVehicleStatus = vehStatusRepo.findAll();
		model.addAttribute("listVehicleStates", listVehicleStatus);
		
		List<DailyRate> listDailyRate = dailyRateRepo.findAll();
		model.addAttribute("listDailyRate", listDailyRate);
		
		//Stream.of(listDailyRate).forEach(s -> System.out.println(s));
		return "main/config";
	}	
	
 	@GetMapping("/cust/newCustState")
	public String showNewCustStateForm(Model model) {
 		CustState custState = new CustState();
 		model.addAttribute("custState", custState);
 		
 		log.info("=====> new custState");
		return "cust/newCustState";
 	}

	@PostMapping(value = "/cust/saveState")
	public String saveCustState(@Valid @ModelAttribute("custState") CustState custState, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "newCustState";
		custStateRepo.save(custState);
		
		log.warn("=====> saveCustState, name: " + custState.getName());
		return "redirect:/main/config";
	}

 	
 	@GetMapping("/veh/newVehSta")
	public String showNewVehStateForm(Model model) {
 		VehStatus vehStatus = new VehStatus();
 		model.addAttribute("vehStatus", vehStatus);
 		
 		log.info("=====> new VehicleStatus");
		return "veh/newVehStatus";
 	}
 	
	@PostMapping(value = "/veh/saveVehStatus")
	public String saveVehStatus(@Valid @ModelAttribute("vehStatus") VehStatus vehStatus, BindingResult bindingResult) {	
		if(bindingResult.hasErrors())
			return "newVehStatus";
		vehStatusRepo.save(vehStatus);
		
		log.warn("=====> saveVehicleStatus, name: " + vehStatus.getName());
		return "redirect:/main/config";
	}


	@GetMapping("/main/newDailyRate")
	public String showNewRateForm(Model model) {
		DailyRate dailyRate = new DailyRate();
		model.addAttribute("dailyRate",dailyRate);
		List<CustState> listCustStates = custStateRepo.findAll();
		model.addAttribute("listCustStates", listCustStates);
		List<VehStatus> listVehicleStatus = vehStatusRepo.findAll();
		model.addAttribute("listVehicleStates", listVehicleStatus);
		
		log.info("=====> new newDailyRate");
		return "main/editDailyRate";
	}
	
	@PostMapping(value = "/main/saveDailyRate")
	public String saveDailyRate(@Valid @ModelAttribute("dailyRate") DailyRate dailyRate, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			log.warn("=====> saveDailyRate, bindingResult.getAllErrors():: " 
					+ bindingResult.getAllErrors());
			return "editDailyRate";
		}
		
		dailyRateRepo.save(dailyRate);
		log.warn("=====> saveDailyRate, rate: $" + dailyRate.getDayrate());
		return "redirect:/main/config";
	}
	
	@GetMapping("/main/rate/{drId}")
	public String editDailyRate(@PathVariable(value = "drId") int drId, Model model) {
		DailyRate dailyRate = dailyRateRepo.getOne(drId);
		model.addAttribute("dailyRate",dailyRate);
		List<CustState> listCustStates = custStateRepo.findAll();
		model.addAttribute("listCustStates", listCustStates);
		List<VehStatus> listVehicleStatus = vehStatusRepo.findAll();
		model.addAttribute("listVehicleStates", listVehicleStatus);
		
		log.info("=====> editDailyRate, id: " + dailyRate.getId());
		return "main/editDailyRate";
		
	}
	@GetMapping("/main/delete/{drId}")
	public String deleteRate(@PathVariable(value = "drId") int drId, Model model) {
		try {
			dailyRateRepo.deleteById(drId);
			log.warn("=====> dailyRateRepo.delete(drId): " + drId);
		} catch (Exception e ) {
			log.warn("=====> dailyRateRepo.delete(drId) => Something went wrong to: " + drId);
            log.error(e.toString());
            model.addAttribute("error", ("Unable to delete!\n" + e.toString()));
            return "error";
		}

		return "redirect:/main/config";
	}

	/* to create new role. http://localhost:8080/emp/roleNew. Only assess by ADMIN */
	@GetMapping("/emp/roleNew")
	public String showNewRoleForm(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		log.info("=====> new Role");
		return "emp/roleNew";
	} 
	
	@PostMapping(value = "emp/roleSave")
	public String saveRole(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "emp/roleNew";

		rolerepo.save(role);
		log.warn("=====> emp/rolesave: " +role.getName());
		return "redirect:/emp";
	}
	

	@GetMapping("/error")
	public String Error() {
		return "error";						
	}	
}
