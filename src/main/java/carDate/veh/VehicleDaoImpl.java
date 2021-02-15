/* ref: 210113B-BankApp/ */

package carDate.veh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleDaoImpl implements VehicleDao {
	
	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Override
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> list = vehicleRepo.findAll();
		System.out.println("***** Vehicle List Size " + list.size());
		return list;
	}

}
