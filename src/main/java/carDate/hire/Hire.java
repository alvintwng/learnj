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

import carDate.book.History;
import carDate.cust.Customer;
import carDate.emp.Employee;
import carDate.inv.Invoice;
import carDate.veh.Vehicle;


@Entity
public class Hire {

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
    
    @ManyToOne
    @JoinColumn(name = "INVID")
    private Invoice invoice;

    @Column(name = "HISTID")
    private Integer histId;

    @Column(name = "EMPID")
    private Integer empId;
    
	/* can't set default false */
    private boolean casedone;
    
    private Integer entity;

	public Hire() {
		super();
	}

	@Override
	public String toString() {
		return ""
		+ "Hire [hireId=" + hireId + ", customer=" + customer + ", vehicle=" + vehicle 
		+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", invoice=" + invoice 
		+ ", casedone=" + casedone  + "]";
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getHistId() {
		return histId;
	}

	public void setHistId(Integer histId) {
		this.histId = histId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public boolean isCasedone() {
		return casedone;
	}

	public void setCasedone(boolean casedone) {
		this.casedone = casedone;
	}

	public Integer getEntity() {
		return entity;
	}

	public void setEntity(Integer entity) {
		this.entity = entity;
	}
}