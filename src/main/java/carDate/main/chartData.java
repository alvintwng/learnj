package carDate.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import carDate.book.History;

@Component
public class chartData {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
	
	public static class Yma {
		private int amount;
		private int mthValue;
		private int yrValue;
	}
	public static class MthAmt {
		private String mthEnum;
		private int tAmount;
		private int yrValue;
		public String getMthEnum() {
			return mthEnum;
		}
		public void setMthEnum(String mthEnum) {
			this.mthEnum = mthEnum;
		}
		public int gettAmount() {
			return tAmount;
		}
		public void settAmount(int tAmount) {
			this.tAmount = tAmount;
		}
		@Override
		public String toString() {
			return "MthAmt [mthEnum=" + mthEnum + ", tAmount=" + tAmount + ", yrValue=" + yrValue + "]";
		}
	}
	
	/* convert History to object YearMonthAmoint yma */
	public static Yma histToYma(History h) {
		
		Yma yma = new Yma();
		yma.amount = (int) h.getAmoint();
		yma.mthValue = h.getRecorded().getMonthValue();
		yma.yrValue = h.getRecorded().getYear();
		
		return yma;
	}
	
	/* Transfer History[] to Yma[] */
	public static List<Yma> HistYrMthAmt(List<History> histList) {

		List<Yma> data = new ArrayList<>();
		for (History h: histList) {;
			data.add(histToYma(h));
		}

		return data;
	}

	/* Sum up the amount for the month */
	public static Integer getMthAmt(List<Yma> ymaList, int yrValue, int mthValue) {
		
		List<Integer> justM = new ArrayList<>();
		for (Yma y: ymaList) {
			if ((y.yrValue == yrValue) && (y.mthValue == mthValue)) {
				justM.add(y.amount);
			}
		};

		int mthSum = justM.stream().reduce(0, Integer::sum);
		return mthSum;
	}
	
	/* getChartData for barChart, via index.html, chart.js*/
	public static List<MthAmt> ChartData(List<History> histList
			, LocalDateTime afterDate , LocalDateTime beforeDate) {
		
		List<Yma> ymaList = HistYrMthAmt(histList);
		List<MthAmt> mthAmtList = new ArrayList<>();
		
		for (LocalDateTime i = afterDate; i.isBefore(beforeDate); i =  i.plusMonths(1)  ) {

			MthAmt m = new MthAmt();
			//String mEnum = i.format(formatter);
			String mEnum = "" + i.getYear() + "/" + String.format("%02d", i.getMonthValue());
			m.mthEnum = mEnum;
			m.tAmount = getMthAmt(ymaList, i.getYear(), i.getMonthValue() );
			m.yrValue = i.getYear();
			mthAmtList.add(m);
		}
		
		//System.out.println("=====> getChartData mthAmt size" + mthAmtList.size());
		//mthAmtList.forEach(m -> System.out.println(m));
		return mthAmtList;

	}

}
