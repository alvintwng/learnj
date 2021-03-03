package carDate.emp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class employeeCrudTest {

	@Autowired
	private EmployeeRepo empRepo; 
	
	@Autowired
	private TestEntityManager entityManager;

//	@Test
//	public void TestCreationOfRoles() {
//		Role roleAdmin = new Role(); roleAdmin.setName("ADMIN");
//		Role roleManager = new Role(); roleManager.setName("MANAGER");
//		Role roleUser = new Role(); roleUser.setName("USER");
//
////		entityManager.persist(roleAdmin);
////		entityManager.persist(roleManager);
////		entityManager.persist(roleUser);
//
//	}
	
	@Test
	public void empDaouserPassIsValid() {
		EmployeeDao empDao;
		// KIV
	}

	@Test
	public void userNTUCisIn() {
		Employee emp = empRepo.findByEmpName("ntuc");
		assertEquals(emp.getPassword().length(), 60); 
	}

	@Test
	public void dateisExpiredTest() {
		Employee emp = empRepo.findByEmpName("ntuc");
		assertTrue(emp.loginIsValid());

	}
	
	@Test
	public void emailIsValidTest() {
		Employee emp = empRepo.findByEmpName("ntuc");
		assertTrue(emp.emailIsValid("ntuc@ntuc.com"));
		assertFalse(emp.emailIsValid("false@com.com"));
	} 

	
	@Test
	public void employeeIsValid() {
		
		String TestPassword = "testPassword";
		Employee employee = new Employee("testEmployee", TestPassword);
		employee.setPassword(TestPassword); // always generate new password of 60chars

		Role role = new Role("newRole");
		employee.addRole(role);

		assertEquals("testEmployee", employee.getEmpName() ); 
		assertEquals(employee.getPassword().length(), 60); 

		assertEquals(role.getName(),  "newRole");
		assertEquals(employee.getRoles().size(), 1);
		assertEquals(employee.getRoles().toArray()[0].toString(),  "newRole");
		
	}
	
	/* Testing the save and delete into Oracle Developer */
	@Disabled
	@Test
	public void testEmployeeRepo() {
		LocalDate d = LocalDate.now();
		Employee emp = new Employee();
		emp.setEmpName("TEST");
		emp.setPassword("TEST");
		emp.setEmpFullName("TESTTEST");
		emp.setPhoneNo("01010101");
		emp.setEmail("testtest@testtest.com");
		emp.setJobTitle("TESTER");
		emp.setUserExpiry(d);
		emp.setPswdExpiry(d.plusDays(365));

		empRepo.save(emp);
		empRepo.findByEmpName("TESTTE"); // dont work
		empRepo.deleteById(emp.getEmpId());
	}

}
