package carDate.book;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import carDate.cust.Customer;
import carDate.veh.Vehicle;

public class Booking {

	private Customer customer;
	private Vehicle vehicle;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStart;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnd;

	private boolean casedone;
	
	public Booking() {
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean isCasedone() {
		return casedone;
	}

	public void setCasedone(boolean casedone) {
		this.casedone = casedone;
	}

	@Override
	public String toString() {
		return "Booking [customer=" + customer + ", vehicle=" 
				+ vehicle + ", dateStart=" + dateStart + ", dateEnd="
				+ dateEnd + ", casedone=" + casedone + "]";
	}

}
