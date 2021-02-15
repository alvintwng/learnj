package carDate.hire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HireRepo extends JpaRepository<Hire, Long> {

}
