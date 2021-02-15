// # last ref to /mFCapStoneProj5/210113A-BankApp/
package carDate.cust;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

//import carDate.hire.Hire;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="CUSTID")  
	private long custId;

	@Column(name = "CUSTNAME")  
	@Size (min = 8, max = 45)
	@NotNull
	private String custName;

	@Column(unique=true)
	@Size (min = 9, max = 9, message="Enter a 9-char code")
	@NotNull
	private String nric;

	@Column( unique=true)
	@Email
	@Size (min = 8, max = 30)
	@NotNull
	private String email;

	@Column(name = "PHONENO", unique=true)
	@Size (min = 8, max = 15)
	@NotNull
	private String phoneNo;

	@Column()  
	@Size (min = 2, max = 30)
	@NotNull(message="Should not be null")
	private String addr1;

	@Column()  
	@Size (min = 2, max = 30)
	@NotNull
	private String addr2;

	@Column()  
	@Size (min = 2, max = 30)
	@NotNull
	private String city;

	@NotNull
	@Column() 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Column(name = "ISACTIVE")
	private boolean isActive;

	@NotNull
	@Column(name="DATEUPD")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateUpd;
	
//	// links this.Customer to another for alternative contact
//    @ManyToOne(fetch = FetchType.EAGER, optional = true)
//    @JoinColumn(name = "CUSTIDLINK", nullable = true)
//    private Customer custLinked;
//
//	// links this.Customer to current hire
//    @ManyToOne(fetch = FetchType.EAGER, optional = true)
//    @JoinColumn(name = "CURRHIREID", nullable = true)
//    private Customer currHire;


//	// links this.Customer to Hires
//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<Hire> hires;
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDate getDateUpd() {
		return dateUpd;
	}
	public void setDateUpd(LocalDate dateUpd) {
		this.dateUpd = dateUpd;
	}
	
}
