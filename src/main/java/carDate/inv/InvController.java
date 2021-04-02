package carDate.inv;

import java.time.LocalDate;

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

import carDate.book.DailyRate;
import carDate.book.DailyRateRepo;
import carDate.book.LocalDateArrayMany;
import carDate.cust.CustomerDao;
import carDate.hire.HireDao;
import carDate.hire.Hire;
import carDate.veh.VehicleDao;

@Controller
public class InvController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private InvMapRepo mapRepo;
	@Autowired
	private CustomerDao custDao;
	@Autowired
	private VehicleDao vehDao;
	@Autowired
	private HireDao hireDao;
	@Autowired
	private DailyRateRepo rateRepo;
	
	@GetMapping("inv/invoice/{id}")
	public String showInvoice(@PathVariable("id") int id, Model model) {
		
		Invoice inv = invoiceDao.getInvoiceById(id);
//		Optional<InvMap> invMap = mapRepo.findById(inv.getInvMapId());
//		Customer cust = custDao.getCustomerById(invMap.get().getCustNameId());
		model.addAttribute("inv", inv);
//		model.addAttribute("cust", cust);
//		
//		
//		System.out.println("=====> inv/invoice, invMapId: " +  inv.getInvMapId());
//		System.out.println("=====> inv/invoice, invMap.toString " + invMap.toString());
//		System.out.println("=====> inv/invoice, customer name: " + cust.getCustName());
		log.info("=====> inv/invoice/" + inv.getId());
		return "inv/invoice";
	}
	
	@GetMapping("/invHire/{hireId}")
	public String invHireForm(@PathVariable(value = "hireId") long hireId, Model model) {

		Hire hire = hireDao.getHireById(hireId);

		Invoice inv = new Invoice();

			inv.setInvNo("AV" + (800000 + hireId) ); // under construction > to get the last invNo
			inv.setHireId((int) hireId);
		LocalDate d = LocalDate.now();
			inv.setDated(d);
			inv.setCustId((int) hire.getCustomer().getCustId());
		float rate = calDayRateByHire(hire);
			inv.setRated(rate);
		float amt = calAmount(hire);
			inv.setAmount(amt);
		String desc1 = hire.getDateStart() + " to " + hire.getDateEnd();
		String desc2 = hire.getVehicle().getVehBrand() + ", " + hire.getVehicle().getVehModel();
		String desc3 = "";
			inv.setDesc1(desc1);
			inv.setDesc2(desc2);
			inv.setDesc3(desc3);

		model.addAttribute("inv", inv);
		
		log.info("=====> invHire, hireId: " + hire.getHireId());
		return "inv/invHire";
	}
	
	@PostMapping(value = "/inv/save")
	public String saveInv(@Valid @ModelAttribute("inv") Invoice inv, BindingResult bindingResult) {	
		if(bindingResult.hasErrors())
			return "hire";

		invoiceDao.save(inv);
		log.info("=====> invoice Save, invId: " + inv.getId());
		
		Hire hire = hireDao.getHireById(inv.getHireId());
		hire.setCasedone(true);
		hireDao.save(hire);
		
		return "redirect:/inv/invoice/" + inv.getId() ;
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
