package carDate.hire;

import java.util.List;

import org.springframework.stereotype.Component;

import carDate.cust.Customer;
import carDate.veh.Vehicle;

@Component
public interface HireDao {
	
	public List<Hire> getAllHires();
	public void save(Hire hires);
	public Hire getHireById(long hireId);
	public void delete(Long hireId);
	public List<Hire> getAllHiresByVehicle(Vehicle vehicle);
	public List<Hire> findAllByCustomer(Customer customer);

}
