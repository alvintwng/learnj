package carDate.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SteamCollector {
	
	@Autowired
	private HistoryRepo histRepo;
	
	//@Disabled
	@Test
	public void testing() {
		int a = 1;
		assertEquals(a, 1);
		
		copiedHist();

	}
	
	public class AmtMth {
		private int amount;
		private int mthValue;
		private String mthEnum;
		private int yrValue;
		private String yrMth;
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public String getMthEnum() {
			return mthEnum;
		}
		public void setMthEnum(String mthEnum) {
			this.mthEnum = mthEnum;
		}
		public int getMthValue() {
			return mthValue;
		}
		public void setMthValue(int month) {
			this.mthValue = month;
		}
		public int getYrValue() {
			return yrValue;
		}
		public void setYrValue(int yrValue) {
			this.yrValue = yrValue;
		}
		public String getYrMth() {
			return yrMth;
		}
		public void setYrMth(String yrMth) {
			this.yrMth = yrMth;
		}
		@Override
		public String toString() {
			return "AmtMth [amount=" + amount + ", mthEnum=" + mthEnum + ", yrMth=" + yrMth + "]";
		}
	}

	
	/* output Integer TotalAmont from of List of pair Month, Amount */
	public static Integer tAmtForMonth(List<AmtMth> amtWkList, Integer month) {
		Integer r =  amtWkList	.stream()
			.filter(e -> e.getMthValue() == month)
			.collect(Collectors.summingInt(AmtMth::getAmount)
					);
		return r;
	}
	
	public static Integer tAmtForYrMth(List<AmtMth> amtWkList, int yrValue, int mthValue) {
		Integer r =  amtWkList	.stream()
			.filter(e -> (e.getYrValue() == yrValue) && (e.getMthValue() == mthValue)  )
			.collect(Collectors.summingInt(AmtMth::getAmount)
					);
//		System.out.println("====> tAmtForYrMth yrMth:" + yrValue + mthValue 
//				+ " r:" + r + " amtWkList size:" + amtWkList.size());
		return r;
	}
	
	public void HistRange(LocalDateTime afterThisDate) {
		History hist = new History();
		List<History> histList = histRepo.findAll();
		List<History> histList2 = histRepo.findAll().subList(120, 127); // <<<< 
	}
	
	/**
	 * The Objective
	 * get array of AmtMth from LocalDate to another LocalDate
	 * Input History Data and 2 LocalDate  
	 * 			(hist, rangeStart, rangeEnd){ get data within this range}
	 * output Array of pair of TheMonth, Total Amount of this month:
	 * 	tAmtForMonth[] name(amtWkList, theMnthList) {  }
	 */
	public void copiedHist() {
		
//		LocalDateTime afterThisDate = LocalDateTime.now().minusYears(1);
		LocalDateTime afterThisDate = LocalDateTime.now().minusMonths(3);
		LocalDateTime beforeThisDate = LocalDateTime.now();
		System.out.println("getMonth: " + beforeThisDate.getMonth()) ;
		System.out.println("getYear: " + beforeThisDate.getYear()) ;
		
		//////// amtWkList
		History hist = new History();
		List<History> histList = histRepo.findAll();
		List<AmtMth> amtWkList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
		for (History h: histList) {
			if (h.getRecorded().isAfter(afterThisDate) &&
				h.getRecorded().isBefore(beforeThisDate)) {
				AmtMth aw = new AmtMth();
				aw.setAmount((int) h.getAmoint());
				aw.setMthValue(h.getRecorded().getMonthValue());
					String mthEnum = h.getRecorded().format(formatter);
				aw.setMthEnum(mthEnum) ;
				aw.setYrValue(h.getRecorded().getYear());
				aw.setYrMth(""+ h.getRecorded().getYear() 
					+ String.format("%02d", h.getRecorded().getMonthValue()) );
				System.out.print(" " +  aw);
				amtWkList.add(aw);
			}
		}
		System.out.println("=====> amtWkList size: " + amtWkList.size());
		
		List<AmtMth> chartRange = new ArrayList<>();
		
		for (LocalDateTime i = afterThisDate; i.isBefore(beforeThisDate); i =  i.plusMonths(1)  ) {
			
			String mthEnum = i.format(formatter);
			System.out.println( mthEnum);
			AmtMth gBar = new AmtMth();
			gBar.setYrValue(i.getYear());
			gBar.setMthEnum(mthEnum); gBar.setMthValue(i.getMonthValue());
			gBar.setYrMth("" + i.getYear() + String.format("%02d", i.getMonthValue()));
			gBar.setAmount(tAmtForYrMth(amtWkList, gBar.getYrValue(), gBar.getMthValue() ) );
			chartRange.add(gBar);

			System.out.println("====> chartRange yrMth:" +gBar.getYrValue() + gBar.getMthValue()
					+ " chartRange size:" + chartRange.size());
		}
		System.out.println("=====> chartRange size: " + chartRange.size());
		
		chartRange.forEach(s -> System.out.println(">>>> " + s));
		
		/**** below testing only ***/
		/** ref: m9JavaSe2/Collectors.md **/
		
		System.out.println("=====> amtWkList stream counting set: " 
				+ amtWkList.stream().collect(Collectors.counting())  );
		
		System.out.println("=====> stream map getMthValue toList  " + amtWkList
				.stream()
				.map(e -> e.getMthValue())
				.collect(Collectors.toList()));
		System.out.println("=====> stream map getMthValue toSet  " + amtWkList
				.stream()
				.map(e -> e.getMthValue())
				.collect(Collectors.toSet()));
		
		List<Integer> data2 = new ArrayList<>();
		data2 = amtWkList
				.stream()
				.filter(e -> (e.getMthValue() == 3 ) && (e.getYrValue() == 2021  ) )
				.map(e -> e.getAmount())
				.collect(Collectors.toList());
		System.out.println("=====> stream filter yrValue 2021 MthValue 3, getAmount " + data2 );
		
		System.out.println("=====> stream grouping mth=tally " + amtWkList
				.stream()
				.collect(
						Collectors.groupingBy(AmtMth::getMthValue,
								Collectors.collectingAndThen(
										Collectors.counting(), f -> f.intValue())
						)));

		List<AmtMth> data = new ArrayList<>();
		data = amtWkList
				.stream()
				.collect(
						Collectors.groupingBy(AmtMth::getMthValue, TreeMap::new,
								Collectors.toList()))
				.firstEntry()
				.getValue() ;
		System.out.println("=====> stream total amth per mth:  " + data );
		
		System.out.println("=====> stream Amount summarizing " + amtWkList
				.stream()
				.collect(Collectors.summingInt(AmtMth::getAmount)
						));	
		
        System.out.println(" =====> stream groupby AmtMth::getYrMonth ::: " + amtWkList
        .stream()
        .collect(Collectors.groupingBy(AmtMth::getYrMth))
        				);
        
		System.out.println("=====> stream filter 3 Total Amount" + amtWkList
				.stream()
				.filter(e -> e.getMthValue() == 3)
				.collect(Collectors.summingInt(AmtMth::getAmount)
						));
				//.collect(Collectors.toList()));
		System.out.println("=====> stream filter 2021 3 Total Amount" + amtWkList
				.stream()
				.filter(e -> (e.getYrValue() == 2021) && (e.getMthValue() == 3)  )
				.collect(Collectors.summingInt(AmtMth::getAmount)
						));
	}
	
}
