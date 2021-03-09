/*  # last ref to /mFCapStoneProj5/210113A-BankApp/
 * 210113B-BankApp/CustomerDaoImpl.java */
package carDate.cust;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<Customer> getAllCustomers() {
		
		List<Customer> list = customerRepo.findAll();

		return list;
	}

	@Override
	public Customer getCustomerById(long custId) {

		Optional<Customer> optional = customerRepo.findById(custId);
		Customer cust = null;

		if (optional.isPresent())
			cust = optional.get();
		else
			throw new RuntimeException(" Employee not found for id :: " + custId);

		return cust;
	}

	@Override
	public void save(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public void delete(Long custId) {
		customerRepo.deleteById(custId);
	}

}
