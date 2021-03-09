package carDate.hire;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Hire {
	
	@Id // this is the primary key for this record
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // defines how this id is to be generated....??
	@Column(name="HIREID")  // The following attribute is linked to this column in the db table
	private long hireId;
	
    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime DtsStart;

    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime DtsEnd;

    @NotNull
	private double dailyRate; 
    
    @NotNull
	private double deposit;
	
    @NotNull
	private double HireFee;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime DtsEndActual;
	
	private double HireFeeActual;

	/* 210308 to check available*/
	private boolean isHired;

	
	public boolean isHired() {
		return isHired;
	}

	public void setHired(boolean isHired) {
		this.isHired = isHired;
	}

	public long getHireId() {
		return hireId;
	}

	public void setHireId(long hireId) {
		this.hireId = hireId;
	}

	public LocalDateTime getDtsStart() {
		return DtsStart;
	}

	public void setDtsStart(LocalDateTime dtsStart) {
		DtsStart = dtsStart;
	}

	public LocalDateTime getDtsEnd() {
		return DtsEnd;
	}

	public void setDtsEnd(LocalDateTime dtsEnd) {
		DtsEnd = dtsEnd;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getHireFee() {
		return HireFee;
	}

	public void setHireFee(double hireFee) {
		HireFee = hireFee;
	}

	public LocalDateTime getDtsEndActual() {
		return DtsEndActual;
	}

	public void setDtsEndActual(LocalDateTime dtsEndActual) {
		DtsEndActual = dtsEndActual;
	}

	public double getHireFeeActual() {
		return HireFeeActual;
	}

	public void setHireFeeActual(double hireFeeActual) {
		HireFeeActual = hireFeeActual;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	
}
/*
 SET DEFINE OFF;
Insert into SUMPROJ.HIRE (HIREID,DTS_END,DTS_END_ACTUAL,DTS_START,HIRE_FEE,HIRE_FEE_ACTUAL,DAILY_RATE,DEPOSIT,IS_HIRED) values (2,to_timestamp('17-FEB-21 10.38.10.851000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('17-FEB-21 10.38.16.013000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('15-FEB-21 10.38.19.848000000 AM','DD-MON-RR HH.MI.SSXFF AM'),100,120,12,500,0);
Insert into SUMPROJ.HIRE (HIREID,DTS_END,DTS_END_ACTUAL,DTS_START,HIRE_FEE,HIRE_FEE_ACTUAL,DAILY_RATE,DEPOSIT,IS_HIRED) values (3,to_timestamp('11-MAR-21 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('11-MAR-21 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('04-MAR-21 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),100,120,12,400,1);
  */

 