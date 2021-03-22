package carDate.hire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carDate.veh.Vehicle;

@Repository
public interface HireRepo extends JpaRepository<Hires, Long> {
	
	List<Hires> findAllByVehicle(Vehicle vehicle); 

}
