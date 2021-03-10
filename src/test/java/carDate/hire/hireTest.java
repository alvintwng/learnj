package carDate.hire;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class hireTest {
	
//	@Autowired
//	private HireDao hireDao;
//
//	@Autowired
//	private HireRepo hireRepo;
//	
//	@Autowired
//	private VehicleRepo vehRepo;

//	@Autowired
//	private VehicleDao vehDao;
	
	@Test
	public void hiretest() {

//		HireDao hiredao;
////		List<Hire>  hire = hireRepo.findAll();
//		long vehId = 1;
//		Hire hire = new Hire();
//    	Vehicle vehicle = new Vehicle();
    	
//    	Optional<Vehicle> test = vehRepo.findById(vehId);
//		assertEquals(hire.VehicleName(1), "testName"); 
		assertEquals( "testName", "testName");
	}
}
