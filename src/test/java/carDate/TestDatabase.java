package carDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import carDate.emp.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestDatabase {

	
	@Autowired
	private TestEntityManager entityManager;
	
	@Disabled
	@Test
	public void TestCreationOfRoles() {
		Role roleSuper = new Role("SUPER");
		Role rolesMonkey = new Role("MONKEY");
		Role rolesHello = new Role("HELLO");

		entityManager.persist(roleSuper);
		entityManager.persist(rolesMonkey);
		entityManager.persist(rolesHello);

	}
}
