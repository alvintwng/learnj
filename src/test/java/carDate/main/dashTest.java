package carDate.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import carDate.hire.Hire;
import carDate.hire.HireDao;
import carDate.hire.HireRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class dashTest {
	
	Optional<Hire> hire = Optional.ofNullable(new Hire());
	HireRepo hireRepo;
	HireDao hireDao;
	
	
	@Test
	public void testing() {
		
//		hire = hireRepo.findById((long) 1);
		
//		dash.test();
		
		//dash.daysHire();
		
		
		int a = 1;
		assertEquals(a, 1);
	}
}
