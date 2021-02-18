/* 210113B-BankApp/CustomerDaoImpl.java 
 * 210127N-BankApp/.../CustomerDaoImpl.java 
 * 210127-SpringAUth/ProductService.java  */
package carDate.emp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
//		return null;
		List <Employee> list = employeeRepo.findAll();
		System.out.println("***** Employee List Size  " + list.size() );
		return list;
	}

	@Override
	public Employee getEmployeeById(long empId) {
		
		Optional <Employee> optional = employeeRepo.findById(empId);
		Employee customer = null;
		
		if(optional.isPresent())
			customer = optional.get();
		else
			throw new RuntimeException(" Employee not found for id :: " + empId);
		
		return customer;		
	}
	
	@Override
	public void save(Employee employee) {
		employeeRepo.save(employee);
	}
	
	public void delete(Long id) {
		employeeRepo.deleteById(id);
	}
}
