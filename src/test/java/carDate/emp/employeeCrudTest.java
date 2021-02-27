package carDate.emp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

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

//	@Autowired
//	private EmployeeDao empDao; 
	
//	@Autowired
//	private TestEntityManager entityManager;
//
//	@Test
//	public void test() {
//	}	
//	
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
	public void employeeIsValid() {
		
		String TestPassword = "testPassword";
		Employee employee = new Employee("testEmployee", TestPassword);
		employee.setPassword(TestPassword); // always generate new password of 60chars
		// System.out.println("=====> employee.setPassword(TestPassword): " + employee.getPassword());
		Role role = new Role("newRole");
		employee.addRole(role);

		assertEquals("testEmployee", employee.getEmpName() ); 
		assertEquals(employee.getPassword().length(), 60); 

		assertEquals(role.getName(),  "newRole");
		assertEquals(employee.getRoles().size(), 1);
		// System.out.println("=====>getRoles().toArray()[0]: " + employee.getRoles().toArray()[0] );

//		assertFalse(employee.isValid());
	}

	@Test
	public void sqlDataisUsernameIsValid() {
//		EmployeeController empCtrl;
//		
//		
//		empCtrl.showNewEmpForm(null)
		
	}

	
/*
 	@Test
	public void accountIsValid() {
		// setup account with a valid set of beneficiaries to prepare for testing
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
		assertTrue(account.isValid());
	}
 * */	
	
}
