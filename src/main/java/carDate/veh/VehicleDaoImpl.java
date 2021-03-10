/* ref: 210113B-BankApp/ */

package carDate.veh;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleDaoImpl implements VehicleDao {
	
	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Override
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> list = vehicleRepo.findAll();

		return list;
	}

 	@Override
	public Vehicle getVehicleById(long vehId) {
		
		Optional <Vehicle> optional = vehicleRepo.findById(vehId);
		Vehicle vehicle = null;
		
		if(optional.isPresent())
			vehicle = optional.get();
		else
			throw new RuntimeException(" Vehicle not found for id :: " + vehId);
		
		return vehicle;		
	}
	
	@Override
	public void save(Vehicle vehicle) {
		 vehicleRepo.save(vehicle);
		
	}

	@Override
	public void delete(Long vehId) {
		vehicleRepo.deleteById(vehId);
	}

}
