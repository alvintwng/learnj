package carDate.cust;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustState {
	
	@Id
	@Column(name = "CUSTSTATEID")  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer custStateId;
	
	@Column(length = 15, nullable = false, unique = true)
	private String name;
	
	@Column(name = "ISDRIVER")  
	private boolean isDriver;

	public CustState() {
		super();
	}

	public Integer getCustStateId() {
		return custStateId;
	}

	public void setCustStateId(Integer custStateId) {
		this.custStateId = custStateId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDriver() {
		return isDriver;
	}

	public void setDriver(boolean isDriver) {
		this.isDriver = isDriver;
	}
	
}
