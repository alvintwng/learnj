package carDate.emp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class empValidTest {
	
	@Autowired
	private EmployeeRepo empRepo; 

	/*
	 * Unable to run/test with DAO state
	 * */
	@Disabled
	@Test
	public void testTest() {
		EmpValid empValid = new EmpValid();
		assertEquals(empValid.test(), "test" );
	}
	@Disabled
	@Test
	public void testDuplicateEmail() {
		EmpValid empValid = new EmpValid();
		assertEquals(empValid.duplicateEmail("ntuc@ntuc.com"), "test" );
	}
	
	@Test
	public void testEmail() {
//		boolean emp = empRepo.existsById((long) 2);
		Employee emp = empRepo.findByEmail("ntuc@ntuc.com");
//		Employee emp = empRepo.findByEmpName("ntuc");
		assertEquals(emp.getEmpName(),"ntuc");
		assertEquals(emp.getPassword().length(), 60);
	}
	
	@Test
	public void testAllEmail() {
		Employee[] empS = empRepo.findAllByEmail("ntuc@ntuc.com");
		assertEquals(empS.length, 1);
		empS = empRepo.findAllByEmail("noSuch@email.com");
		assertEquals(empS.length, 0);
	}
}
