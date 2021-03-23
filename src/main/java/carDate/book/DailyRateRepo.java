package carDate.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRateRepo extends JpaRepository<DailyRate, Integer> {

}
