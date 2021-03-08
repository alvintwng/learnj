package carDate.veh;

import org.springframework.data.jpa.repository.JpaRepository;

import carDate.emp.Role;

public interface VehStatusRepo extends JpaRepository<VehStatus, Integer> {

}
