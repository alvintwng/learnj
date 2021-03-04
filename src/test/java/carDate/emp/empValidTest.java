package carDate.emp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)	// for Dao testing 
@Transactional					// for Dao testing
@SpringBootTest					// for Dao testing
public class empValidTest {
	
	@Autowired
	private EmployeeRepo empRepo; 

	@Autowired
	private EmployeeDao empDao; 
	
	@Test
	public void empDaoGetAllEmpTest() {
		if (!empDao.getAllEmployees().isEmpty()) {
			List<Employee>  employees = empDao.getAllEmployees();
			assertThat(employees).isNotNull();
		}
	}
	
	/* Unable to run/test the bean with DAO/Repo state 
	 * current test thru about.html/test */
	@Disabled
	@Test
	public void testTest() {
		EmpValid empValid = new EmpValid();
		assertEquals(empValid.test(), "test" );
	}
	
	/* Unable to run/test with DAO/Repo state 
	 * current test thru about.html/test */
	@Disabled
	@Test
	public void testDuplicateEmail() {
		EmpValid empValid = new EmpValid();
		assertTrue(empValid.duplicateEmail("ntuc@ntuc.com"));
		assertFalse(empValid.duplicateEmail("very@newEmail.com"));
	}
	
	/* test on empRepo.findAllEmail(email) */
	@Test
	public void testRepoFindEmail() {
		Employee emp = empRepo.findByEmail("ntuc@ntuc.com");
		assertEquals(emp.getEmpName(),"ntuc");
		assertEquals(emp.getPassword().length(), 60);
	}
	
	/* test on empRepo.findAllByEmail(email) */
	@Test
	public void testRepoFindAllEmail() {
		Employee[] empS = empRepo.findAllByEmail("ntuc@ntuc.com");
		assertEquals(empS.length, 1);
		empS = empRepo.findAllByEmail("noSuch@email.com");
		assertEquals(empS.length, 0);
	}

	/* test on empDao.getAllByEmail(email) 
	 * ref: https://dzone.com/articles/unit-testing-dao-service-and-controller-in-spring
	 * */
	@Test
	public void testDaoFindAllEmail() {
		Employee[] empS = empDao.getAllByEmail("ntuc@ntuc.com");
		assertThat(empS).isNotNull();
	}
}
