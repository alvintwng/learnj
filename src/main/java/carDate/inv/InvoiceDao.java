package carDate.inv;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

@Component
public interface InvoiceDao {
	public Invoice getInvoiceById(int invId);

	public void save(@Valid Invoice invoice);
	
	public List<Invoice> getAllInvoices(); // to determine Invoice No.

}
