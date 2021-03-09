// # last ref to /mFCapStoneProj5/210113A-BankApp/
package carDate.cust;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface CustomerDao {
	public List<Customer> getAllCustomers();
	public Customer getCustomerById(long custId);
	public void save(Customer customer);
	public void delete(Long custId);
}
