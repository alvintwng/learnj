/*  # last ref to /mFCapStoneProj5/210113A-BankApp/
 * 210113B-BankApp/CustomerDaoImpl.java */
package carDate.cust;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
////		customerRepository.findAll();
//		return null;
		
		List<Customer> list = customerRepository.findAll();
		System.out.println("***** Customer List Size " + list.size());
		return list;
	}

}
