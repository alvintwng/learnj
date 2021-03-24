package carDate.hire;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carDate.cust.Customer;
import carDate.veh.Vehicle;

@Service
public class HireDaoImpl implements HireDao {
	
	@Autowired
	private HireRepo hireRepo;
	
	@Override
	public List<Hires> getAllHires() {
		
		List<Hires> list = hireRepo.findAll();
		
		//System.out.println("***** Hire List Size " + list.size());
		return list;
	}

	@Override
	public void save(Hires hire) {
		hireRepo.save(hire);

	}

	@Override
	public Hires getHireById(long hireId) {
		
		Optional <Hires> optional =  hireRepo.findById(hireId);
		Hires h = null;
		
		if (optional.isPresent())
			h = optional.get();
		else
			throw new RuntimeException(" Hire not found for id :: " + hireId);
		
		return h;
	}

	@Override
	public void delete(Long hireId) {
		hireRepo.deleteById(hireId);
	}

	@Override
	public List<Hires> getAllHiresByVehicle(Vehicle vehicle) {
		List<Hires> list = hireRepo.findAllByVehicle(vehicle);
		return list;
	}

	@Override
	public List<Hires> findAllByCustomer(Customer customer) {
		List<Hires> list = hireRepo.findAllByCustomer(customer);
		return list;
	}

}
