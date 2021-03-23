package carDate;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import carDate.book.DailyRate;
import carDate.book.DailyRateRepo;
import carDate.cust.CustState;
import carDate.cust.CustStateRepo;
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

	@GetMapping("/config")
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
		
		log.info("=====> saveCustState, name: " + custState.getName());
		return "redirect:/config";
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
		
		log.info("=====> saveVehicleStatus, name: " + vehStatus.getName());
		return "redirect:/config";
	}


	@GetMapping("newDailyRate")
	public String showNewRateForm(Model model) {
		DailyRate dailyRate = new DailyRate();
		model.addAttribute("dailyRate",dailyRate);
		List<CustState> listCustStates = custStateRepo.findAll();
		model.addAttribute("listCustStates", listCustStates);
		List<VehStatus> listVehicleStatus = vehStatusRepo.findAll();
		model.addAttribute("listVehicleStates", listVehicleStatus);
		
		log.info("=====> new newDailyRate");
		return "book/newDailyRate";
	}
	
	@PostMapping(value = "saveDailyRate")
	public String saveDailyRate(@Valid @ModelAttribute("dailyRate") DailyRate dailyRate, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "newDailyRate";
		dailyRateRepo.save(dailyRate);

		log.info("=====> saveDailyRate, rate: $" + dailyRate.getDayrate());
		return "redirect:/config";
	}
}
