package carDate.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRateRepo extends JpaRepository<DailyRate, Integer> {
	DailyRate[] findAllByCustCatId(long custCatId);
	DailyRate findByVehClassId(long vehClassId);
}
