/* 210113B-BankApp/CustomerRepository */

package carDate.emp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	Employee findByEmpName(String empName);
	Employee findByEmail(String email);
	Employee[] findAllByEmail(String email);
}
