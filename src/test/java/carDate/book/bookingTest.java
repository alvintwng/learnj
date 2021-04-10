package carDate.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import carDate.hire.Hire;
import carDate.hire.HireRepo;
import carDate.veh.Vehicle;
import carDate.veh.VehicleRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class bookingTest {
	
	@Autowired
	private HireRepo hireRepo;
	@Autowired
	private VehicleRepo vehRepo;
	
	private static LocalDateArrayMany lda;
	
	@Test
	public void startEndDayNotWithinHireDates() {

		
		Vehicle veh  = new Vehicle();
//				vehRepo.findById((long) 5); // null ???

		List<Vehicle> listVeh = vehRepo.findAll();

//		for (Vehicle v : listVeh) {
//			System.out.println("=====> datesCrash,veh: "+ v.getVehId() +":"+v.getVehLicPlate());
//		}
		veh = listVeh.get(3);
		
		List<Hire> listHires =  hireRepo.findAllByVehicle(veh);
		assertThat(listHires).isNotNull();
		
		List<Booking> listBooks = new ArrayList<>();
		
		for (Hire h : listHires) {
			Booking b = new Booking();
			b.setDateEnd(h.getDateEnd());
			b.setDateStart(h.getDateStart());
			b.setCustomer(h.getCustomer());
			b.setVehicle(h.getVehicle());
			listBooks.add(b);
		}
		
		// via check each hired dates that crashed with new start or end date
		LocalDate n= LocalDate.now();
		LocalDate newStart = n.plusDays(0); // new Start Date
		LocalDate newEnd = newStart.plusDays(5); // new End Date
		System.out.println("=====> NewStartDate: " + newStart 
				+ " newEndDate " + newEnd);
		
		for (Booking b: listBooks) {
			Boolean crashHireStartTrue 	=  LocalDateArrayMany.chkCrashdDates( b.getDateStart(), newStart,newEnd );
			Boolean crashHireEndTrue 	=  LocalDateArrayMany.chkCrashdDates( b.getDateEnd(), newStart,newEnd );
			Boolean crashNewInsideTrue 	=  LocalDateArrayMany.chkCrashdDates(newStart, b.getDateStart(), b.getDateEnd() );
		
			System.out.println("====> hired " + b.getDateStart()  + " to " + b.getDateEnd() 
			+ " crashed: " + crashHireStartTrue + " "+ crashHireEndTrue + " " + crashNewInsideTrue);
		}
		
		System.out.println("Is today crash within booking dates?");

		
	}

	//@Disabled
	@Test
	public void testing() {
		int a = 1;
		assertEquals(a, 1);

	}

	@Disabled @Test
	public void hireGetTest() {
		List<Hire> listHires =  hireRepo.findAll();
		assertThat(listHires).isNotNull();
		
		Hire hire = listHires.get(1);
		assertThat(hire).isNotNull();
	}
	

	@Test
	public void checkValidDates() {
		LocalDate s= LocalDate.now();
		LocalDate e = s.plusDays(5);

		assertEquals(lda.checkValidDates(s,e), true);
		assertEquals(lda.checkValidDates(s,s), true);
		assertEquals(lda.checkValidDates(e,e), true);
		assertEquals(lda.checkValidDates(e,s), false);
	}
	@Test
	public void datesCrash() {	
		LocalDate s= LocalDate.now();	// Crashed Date
		LocalDate e = s.plusDays(5);	// Before Crashed Date
		LocalDate c = s.plusDays(3);	// After Crashed Date
		
		assertThat(lda.chkCrashdDates(c, s, e) ).isTrue();
		assertThat(lda.chkCrashdDates(c, s, s) ).isFalse();
		assertThat(lda.chkCrashdDates(c, c, c) ).isTrue();
		assertThat(lda.chkCrashdDates(c, e, e) ).isFalse();

	}
	
	@Test
	public void copiedArray() {
		Vehicle veh  = new Vehicle();
		//vehRepo.findById((long) 5); // result: null 
		List<Vehicle> listVeh = vehRepo.findAll();

		veh = listVeh.get(0); // let say 0		
		List<Hire> listHires =  hireRepo.findAllByVehicle(veh);
		assertThat(listHires).isNotNull();
		
		List<Booking> listBooks = new ArrayList<>();
		
		for (Hire h : listHires) {
			Booking b = new Booking();
			b.setDateEnd(h.getDateEnd());
			b.setDateStart(h.getDateStart());
			b.setCustomer(h.getCustomer());
			b.setVehicle(h.getVehicle());
			listBooks.add(b);
		}

		for (Booking b: listBooks) {
			System.out.println("=====> b startDate: " + b.getDateStart());
		}
		assertThat(listBooks).isNotNull();
	}
}
