package carDate.emp;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import org.junit.jupiter.api.Test;
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
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void test() {
	}	
	
	@Test
	public void TestCreationOfRoles() {
		Role roleAdmin = new Role(); roleAdmin.setName("ADMIN");
		Role roleManager = new Role(); roleManager.setName("MANAGER");
		Role roleUser = new Role(); roleUser.setName("USER");

//		entityManager.persist(roleAdmin);
//		entityManager.persist(roleManager);
//		entityManager.persist(roleUser);

	}
	
}
