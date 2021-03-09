package carDate.cust;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustStateRepo extends JpaRepository<CustState, Integer> {

}

 