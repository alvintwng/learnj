/* 210113B-BankApp/CustomerDao.java */

package carDate.emp;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface EmployeeDao {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(long empId);
	void save(Employee employee);
	void delete(Long id);

}
