package carDate.emp;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="EMPLOYEEID")  
	private long empId;

	@Column(name = "EMPNAME", nullable = false, unique = true)
	@Size (min = 4, max = 8)
	@NotNull
	private String empName;

	@NotNull
	private String password;

	@Size (min = 4, max = 45)
	@NotNull
	private String empFullName;

	@Size (min = 8, max = 15)
	@NotNull
	private String phoneNo;

	@Column(unique=true)
	@Email
	@Size (min = 8, max = 30)
	@NotNull
	private String email;

	@Size (min = 5, max = 30)
	@NotNull
	private String jobTitle;

	@Column(name = "ISACTIVE") 
	private boolean isActive;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate userExpiry;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pswdExpiry;


	public Employee() {}
	
	
	public Employee(String empName, String password) {
		super();
		this.empName = empName;
		this.password = password;
	}
	
	
	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}


	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmpFullName() {
		return empFullName;
	}
	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDate getUserExpiry() {
		return userExpiry;
	}
	public void setUserExpiry(LocalDate userExpiry) {
		this.userExpiry = userExpiry;
	}
	public LocalDate getPswdExpiry() {
		return pswdExpiry;
	}
	public void setPswdExpiry(LocalDate pswdExpiry) {
		this.pswdExpiry = pswdExpiry;
	}


}
