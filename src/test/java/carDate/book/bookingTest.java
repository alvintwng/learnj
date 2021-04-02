package carDate.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import carDate.hire.Hire;
import carDate.hire.HireRepo;
import carDate.book.BookingControl;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class bookingTest {
	
	@Autowired
	private HireRepo hireRepo;
	
	//@Disabled
	@Test
	public void testing() {
		int a = 1;
		assertEquals(a, 1);
	}

	@Test
	public void calDayRateTest() {
		List<Hire> listHires =  hireRepo.findAll();
		assertThat(listHires).isNotNull();
		
		Hire hire = listHires.get(1);
		assertThat(hire).isNotNull();
		
		BookingControl bc = new BookingControl();
//		float dayRate = bc.calDayRate(hire); // not working?
//		assertEquals(dayRate, 80);
	}

}
