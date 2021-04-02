package carDate.inv;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDaoImpl implements InvoiceDao{
	
	@Autowired
	private InvoiceRepository invRepo;
	
	@Override
	public Invoice getInvoiceById(int invId) {
		
		Optional<Invoice> optional = invRepo.findById(invId);
		Invoice inv = null;
		
		if (optional.isPresent())
			inv = optional.get();
		else
			throw new RuntimeException(" Invoice not found for id:: " + invId);
		
		return inv;
		
	}

	@Override
	public void save(@Valid Invoice invoice) {
		invRepo.save(invoice);
		
	}
}

