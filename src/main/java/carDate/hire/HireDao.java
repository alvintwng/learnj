package carDate.hire;

import java.util.List;

import org.springframework.stereotype.Component;

import carDate.veh.Vehicle;

@Component
public interface HireDao {
	
	public List<Hires> getAllHires();
	public void save(Hires hires);
	public Hires getHireById(long hireId);
	public void delete(Long hireId);
	public List<Hires> getAllHiresByVehicle(Vehicle vehicle);

}
