package carDate.hire;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import carDate.cust.Customer;
import carDate.veh.VehStatus;
import carDate.veh.Vehicle;
import carDate.veh.VehicleDao;

@Entity
public class Hires {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="HIREID")
	private long hireId;

    // @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @ManyToOne
    @JoinColumn(name = "CUSTID", nullable = false )
	private Customer customer;
 
    @ManyToOne
    @JoinColumn(name = "VEHID", nullable = false)
	private Vehicle vehicle;

    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="DATESTART")
    private LocalDate dateStart;

    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="DATEEND")
	private LocalDate dateEnd;

	/* can't set default false */
    private boolean casedone;


	public Hires() {
		super();
	}

	public long getHireId() {
		return hireId;
	}

	public void setHireId(long hireId) {
		this.hireId = hireId;
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

    /* K I V 
    public String VehicleName(long vehId) {
    	String name = "testName";
//    	Vehicle vehicle = new Vehicle();
//    	VehicleRepo vehDao;
//    	Vehicle test = vehDao.getVehicleById(vehId);
    	return name;
    } */
    

}
