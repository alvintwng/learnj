package carDate.book;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@Column(name="HIREID")
	private long hireId;
	
	@Column(name="CUSTID")
	private long custId;
	
	@Column(name="VEHID")
	private long vehId;
	
	@Column(name="DATESTART")
	private LocalDate dateStart;
	
	@Column(name="DATEEND")
	private LocalDate dateEnd;
	
	@Column(name="CUSTNRIC")
	private String custNric;
	
	@Column(name="CUSTCATID")
	private Integer custCatId;
	
	@Column(name="VEHCLASSID")
	private Integer vehClassId;
	
	private float dayrate;
	
	private float amoint;
	
	private LocalDateTime recorded;

	public History() {
		super();
	}

	
}
