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
	public List<Hire> getAllHires() {
		
		List<Hire> list = hireRepo.findAll();
		
		//System.out.println("***** Hire List Size " + list.size());
		return list;
	}

	@Override
	public void save(Hire hire) {
		hireRepo.save(hire);

	}

	@Override
	public Hire getHireById(long hireId) {
		
		Optional <Hire> optional =  hireRepo.findById(hireId);
		Hire h = null;
		
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
	public List<Hire> getAllHiresByVehicle(Vehicle vehicle) {
		List<Hire> list = hireRepo.findAllByVehicle(vehicle);
		return list;
	}

	@Override
	public List<Hire> findAllByCustomer(Customer customer) {
		List<Hire> list = hireRepo.findAllByCustomer(customer);
		return list;
	}

}
