package carDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A system test that verifies the components of the RewardNetwork application
 * work together to reward for dining successfully. Uses Spring to bootstrap the
 * application for use in a test environment using the development in-memory
 * database and JDBC implementations of the repositories.
 */
@SpringJUnitConfig(classes=TestInfrastructureConfig.class)
//@ActiveProfiles({ "local", "jdbc" })
public class DevCarDateTests {

	/**
	 * The object being tested.
	 */
//	@Autowired
//	private Employee employee;
	
	private Integer TestValue = 11;

	@Test
	@DisplayName("test if works")
	public void testToday() {

		assertEquals(TestValue, 11);
	}
}