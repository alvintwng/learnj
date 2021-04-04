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
import carDate.hire.Hire;
import carDate.hire.HireDao;

@Controller
public class InvController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private InvoiceDao invoiceDao;
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
		log.info("=====> inv/invoice/" + inv.getInvId());
		return "inv/invoice";
	}
	
	@GetMapping("inv/invPaid/{id}")
	public String paidInv(@PathVariable("id") int id) {
		Invoice inv = new Invoice();
			inv = invoiceDao.getInvoiceById(id);
		LocalDate d = LocalDate.now();
			inv.setPaidDate(d);
			inv.setPaymtDone(true);
		invoiceDao.save(inv);
		log.info("=====> invoice PaidDate, update Inv: " + inv.getInvId());
		
		Hire hire = hireDao.getHireById(inv.getHireId());
		hire.setCasedone(true);
		hireDao.save(hire);
		log.info("=====> invoice PaidDate, update Hire: " + hire.getHireId());

		return "redirect:/inv/invoice/" + id ;
	}

	
	@GetMapping("/invHire/{hireId}")
	public String invHireForm(@PathVariable(value = "hireId") long hireId, Model model) {

		Hire hire = hireDao.getHireById(hireId);

		Invoice inv = new Invoice();
			inv.setInvNo("CZ" + (10000 + hireId) ); // under construction > to get the last invNo
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
	public String saveNewInv(@Valid @ModelAttribute("inv") Invoice inv, BindingResult bindingResult) {	
		if(bindingResult.hasErrors())
			return "hire";

		invoiceDao.save(inv);
		log.warn("=====> invoice SaveNew, invId: " + inv.getInvId());
		
		Hire hire = hireDao.getHireById(inv.getHireId());
		//hire.setCasedone(true);
		hire.setInvoice(inv);
		hireDao.save(hire);
		log.info("=====> invoice Save, hire updated: " + hire.getHireId());

		//System.out.println("=====> inv/save> hire invId: " + hire.getInvoice().getInvNo() );
		return "redirect:/inv/invoice/" + inv.getInvId() ;
	}
	
	@GetMapping("/inv/invEdit/{invId}")
	public String editInv(@PathVariable("invId") int invId, Model model) {
		Invoice inv = invoiceDao.getInvoiceById(invId);
		
		LocalDate thisdate = LocalDate.now();
			inv.setPaidDate(thisdate);
		
		model.addAttribute("inv", inv);
		log.info("=====> inv/invEdit/invId: " + inv.getInvId());
		return "inv/invEdit";
	}

	@PostMapping(value = "/inv/saveEdit")
	public String saveInvEdit(@Valid @ModelAttribute("inv") Invoice inv, BindingResult bindingResult) {	
		if(bindingResult.hasErrors()) {
			log.warn("=====> book/save Errors():: " 
					+ bindingResult.getAllErrors());
			return "/403";
		}

		invoiceDao.save(inv);
		log.warn("=====> invoice SaveEdit, invId: " + inv.getInvId());
		return "redirect:/inv/invoice/" + inv.getInvId() ;
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
