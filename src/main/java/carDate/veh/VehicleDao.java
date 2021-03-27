/* ref: 210113B-BankApp/ */

package carDate.veh;

import java.util.List;

public interface VehicleDao {
	public List<Vehicle> getAllVehicles();
	public Vehicle getVehicleById(long vehId);
	public void save(Vehicle vehicle);
	public void delete(long vehId);

}
