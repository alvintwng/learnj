package carDate.book;

import java.util.List;

import carDate.hire.Hires;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class LocalDateArrayMany {
	
	/** Return String[] of d/M/yyyy dates,  from List<Blockdates> 
	 * with LocalDate startDate and Integer period
     * pojo Blockdates with LocalDate bdate (startdate), 
     * 		and LocalDate edate (enddate);
     *
     * @param
     * @return String[] **/
	public static String[] allListsToDMY(List<Hires> listBddates) {

		//listBddates.forEach(b -> System.out.println("Dates blocked: " + b.getBdate() 
		//								+ " to " + b.getEdate() ));
		
		//listBddates	.forEach(b -> Stream.of(listDates(b.getBdate(),b.getEdate()))
		//			.forEach(s -> System.out.println("Stream :: " + s)));

		LocalDate dummyDate = LocalDate.parse("2021-04-01");
		LocalDate[] mergedates;
		mergedates = getDatesFromStart(dummyDate, 0);
		
		for (Hires bd: listBddates){

			LocalDate[] ld = getDatesStartToEnd(bd.getDateStart(), bd.getDateEnd().plusDays(1));
			mergedates = mergeDatelists(mergedates, ld );

			}

		//Stream.of(mergedates).forEach(s -> System.out.println("mergedates :: " + s));
		return  listToDMY(mergedates);
		
	}

	
	/** return date format d/M/yyyy from LocalDate[] 
	 * @param
	 * @return String[]	 **/
	public static String[] listToDMY(LocalDate[] listdates) {
		
		String[] finalDates= new String[listdates.length]; // size up first
	
		DateTimeFormatter dTF; 
		dTF = DateTimeFormatter.ofPattern("d/M/yyyy");

		for(int i=0;i<listdates.length;i++) {
			finalDates[i]=dTF.format(listdates[i]);
			//System.out.println("=====> finalDates: " + finalDates[i]);
		}

		return finalDates;
		
	}
		
	/** Return LocaDate[] of merge LocalDate[] A and LocalDate[] B
	 * @param
	 * @return LocalDate[] **/
	public static LocalDate[] mergeDatelists(LocalDate[] listA, LocalDate[] listB) {
		int length = listA.length + listB.length;
		LocalDate[] mergedates = new LocalDate[length];
		int pos =0;
		for (LocalDate element : listA)
		{ mergedates[pos] = element; pos ++;}
		for (LocalDate element : listB)
		{ mergedates[pos] = element; pos ++;}
		//Stream.of(mergedates).forEach(s -> System.out.println("Stream :: " + s));
		
		return mergedates;
	}
	
	/** return LocalDate[] array all dates from LocalDate start and end (exclude)
	 * @param
	 * @return LocalDate[] **/
	public static LocalDate[] getDatesStartToEnd(LocalDate start, LocalDate end) {
		Integer getDays = getDays(start, end);
		LocalDate[] listDate = getDatesFromStart(start, getDays);
		return listDate;
	}
	
	/** return Integer of days between LocalDate start date and end date 
	 * @param
	 * @return Integer	 **/
	public static Integer getDays(LocalDate start, LocalDate end) {
		Period period = Period.between(start, end);
		Integer getDays = period.getDays();
		return getDays;
	}

	/** return LocalDate[] with range of LocalDate start till end 
	 * @param
	 * @return LocalDate[]	 **/
	public static LocalDate[] getDatesFromStart(LocalDate start, Integer daysNo) {
		LocalDate[] dateRange = new LocalDate[daysNo];
		for(int i=0;i<daysNo;i++) {
			dateRange[i] = start.plusDays(i);
		}
		
		return dateRange;
	}
}
