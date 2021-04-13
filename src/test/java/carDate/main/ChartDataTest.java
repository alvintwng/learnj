package carDate.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import carDate.book.History;
import carDate.book.HistoryRepo;
import carDate.main.chartData.MthAmt;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ChartDataTest {
	
	//Optional<Hire> hire = Optional.ofNullable(new Hire());
	@Autowired
	private HistoryRepo histRepo;

	@Test
	public void testing() {
		int a = 1;
		assertEquals(a, 1);
	}
	
	@Test
	public void barChartdataTest() {
		List<History> histList = histRepo.findAll();

		LocalDateTime afterDate = LocalDateTime.now().minusYears(1);
		LocalDateTime beforeDate = LocalDateTime.now();
		
		List<MthAmt> mthAmtList = new ArrayList<>();
		mthAmtList = chartData.ChartData(histList,afterDate,beforeDate);
		
		assertThat(mthAmtList).isNotNull();

	}
}
