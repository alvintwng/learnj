package carDate.inv;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.lowagie.text.DocumentException;

import carDate.book.DailyRate;
import carDate.book.DailyRateRepo;
import carDate.book.LocalDateArrayMany;
import carDate.cust.Customer;
import carDate.cust.CustomerDao;
import carDate.hire.Hire;
import carDate.hire.HireDao;

@Controller
public class InvController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private InvPaymtRepo invPaymtRepo;
	@Autowired
	private HireDao hireDao;
	@Autowired
	private DailyRateRepo rateRepo;
	@Autowired
	private CustomerDao custDao;
	
	@GetMapping("inv/invList")
	public String showAllInvoices(Model model) {
		List<Invoice> invList = invoiceDao.getAllInvoices();
		model.addAttribute("invList", invList);
		log.info("=====> inv/invList > size:" + invList.size());
		return "inv/invList";
	}
	
	@GetMapping("inv/invoice/{id}")
	public String showInvoice(@PathVariable("id") int id, Model model) {
		
		Invoice inv = invoiceDao.getInvoiceById(id);
		Customer cust = custDao.getCustomerById(inv.getCustId());
		
		model.addAttribute("inv", inv);
		model.addAttribute("cust", cust);
		log.info("=====> inv/invoice/" + inv.getInvId());
		return "inv/invoice";
	}
	
	@GetMapping("inv/invPaid/{id}")
	public String paidInv(@PathVariable("id") int id) {
		Invoice inv = new Invoice();
			inv = invoiceDao.getInvoiceById(id);
		InvPaymt invpaymt = new InvPaymt();
			invpaymt = inv.getInvPaymt();
		LocalDate d = LocalDate.now();
			invpaymt.setPaidDate(d);
			inv.setPaymtDone(true);

		invPaymtRepo.save(invpaymt);
		invoiceDao.save(inv);

		Hire hire = hireDao.getHireById(inv.getHireId());
		hire.setCasedone(true);
		hireDao.save(hire);
		
		log.info("=====> invoice PaidDate, update invPaymt Id:" + invpaymt.getInvPaymtId() 
			+ ", update Hire Id:" + hire.getHireId());

		return "redirect:/inv/invoice/" + id ;
		//return "redirect:/hire";
	}

	/* from hireEdit */
	@GetMapping("/invHire/{hireId}")
	public String invHireForm(@PathVariable(value = "hireId") long hireId, Model model) {

		Hire hire = hireDao.getHireById(hireId);
		List<Invoice> invList = invoiceDao.getAllInvoices(); // create for next invNo

		Invoice inv = new Invoice();
			int invdigits = invList.size() + 1;
			inv.setInvNo("CZ" + (10000 + invdigits) );
			inv.setHireId((int) hireId);
		LocalDate d = LocalDate.now();
			inv.setDated(d);
			inv.setCustId((int) hire.getCustomer().getCustId());
		float rate = calDayRateByHire(hire);
			inv.setRated(rate);
			
		InvPaymt invpaymt = new InvPaymt();	
		float amt = calAmount(hire);							
			invpaymt.setAmount(amt);

		String desc1 = hire.getDateStart() + " to " + hire.getDateEnd();
		String desc2 = hire.getVehicle().getVehBrand() + ", " + hire.getVehicle().getVehModel();
		String desc3 = "";
			inv.setDesc1(desc1);
			inv.setDesc2(desc2);
			inv.setDesc3(desc3);
			inv.setInvPaymt(invpaymt);
			
		model.addAttribute("inv", inv);

		log.info("=====> invHire.html, amount:"+ invpaymt.getAmount() +", hireId:"+ hire.getHireId());
		return "inv/invHire";	// post > inv/newSave
	}

	@PostMapping(value = "/inv/newSave")
	public String saveNewInv(@Valid @ModelAttribute("inv") Invoice inv, BindingResult bindingResult) {	
		if(bindingResult.hasErrors())
			return "hire";

		InvPaymt invPaymt = new InvPaymt();
			invPaymt = inv.getInvPaymt();
			invPaymt.setArchive(false);

		invPaymtRepo.save(invPaymt); //Save the transient instance before flushing inv
		invoiceDao.save(inv); 
			invPaymt.setInvid(inv.getInvId());
		invPaymtRepo.save(invPaymt); 
		
		Hire hire = hireDao.getHireById(inv.getHireId());
			hire.setInvoice(inv);
		hireDao.save(hire);

		log.warn("=====> inv/newSave > hire.invNo:" + hire.getInvoice().getInvNo() 
				+", invId:"+ inv.getInvId() 
				+", invPaymtId:"+ invPaymt.getInvPaymtId() 
				+", hireId:"+ hire.getHireId());

		return "redirect:/inv/invoice/" + inv.getInvId() ;
		//return "redirect:/hire";
	}
	
	@GetMapping("/inv/invEdit/{invId}")
	public String editInv(@PathVariable("invId") int invId, Model model) {
		
		Invoice inv = invoiceDao.getInvoiceById(invId);
		model.addAttribute("inv", inv);

		log.info("=====> inv/invEdit > invId:" 
				+ inv.getInvId() +", invPaymtId:"+ inv.getInvPaymt().getInvPaymtId() );
		return "inv/invEdit";
	}

	@PostMapping(value = "/inv/saveEdit")
	public String saveInvEdit(@Valid @ModelAttribute("inv") Invoice inv, HttpServletRequest request) {	

		String invPaymtAmount = request.getParameter("invPaymtAmount");
		String invPaymtPaidDate = request.getParameter("invPaymtPaidDate");
		float amt = Float.parseFloat(invPaymtAmount);
		LocalDate pymtDate = LocalDate.parse(invPaymtPaidDate);
		
		InvPaymt invPaymt = new InvPaymt();
		invPaymt = inv.getInvPaymt();
		invPaymt.setAmount(amt);
		invPaymt.setPaidDate(pymtDate);

		invoiceDao.save(inv);
		invPaymtRepo.save(invPaymt);
		
		log.warn("=====> invoice SaveEdit, invId:" + inv.getInvId()
			+", invPaymtId:"+ invPaymt.getInvPaymtId());
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
	
    @GetMapping("/inv/export/pdf/{invId}")
    public void exportToPDF(@PathVariable("invId") int invId, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        //List<Users> listUsers = repo.findAll();
        	
    	Invoice inv = invoiceDao.getInvoiceById(invId);
    	InvCust invCust = new InvCust();
    	invCust.setInvoice(inv);
        	
    	log.warn("=====> /inv/export/pdf/> pdf: " + headerValue);
         
		UserPDFExporter exporter = new UserPDFExporter(invCust);
		exporter.export(response);
    }
    
	public  class InvCust {
		private Invoice inv;
		public Invoice getInv() {
			return inv;
		}
		public void setInvoice(Invoice inv) {
			this.inv = inv;
		}
		public Customer getCust() {
			Customer cust = new Customer();
			cust = custDao.getCustomerById(inv.getCustId());
			return cust;
		}
	}
}
