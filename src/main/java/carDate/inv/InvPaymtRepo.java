package carDate.inv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvPaymtRepo extends JpaRepository<InvPaymt, Integer>{

}
