/* 210113B-BankApp/CustomerDao.java */

package carDate.emp;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface EmployeeDao {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(long empId);
	public void save(Employee employee);
	public void delete(Long id);
	public Employee findByEmail(String email);
	public Employee[] getAllByEmail(String email);

}
