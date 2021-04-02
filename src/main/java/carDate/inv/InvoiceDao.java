package carDate.inv;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

@Component
public interface InvoiceDao {
	public Invoice getInvoiceById(int invId);

	public void save(@Valid Invoice invoice);

}
