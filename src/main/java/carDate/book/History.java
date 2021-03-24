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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHireId() {
		return hireId;
	}

	public void setHireId(long hireId) {
		this.hireId = hireId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getVehId() {
		return vehId;
	}

	public void setVehId(long vehId) {
		this.vehId = vehId;
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

	public String getCustNric() {
		return custNric;
	}

	public void setCustNric(String custNric) {
		this.custNric = custNric;
	}

	public Integer getCustCatId() {
		return custCatId;
	}

	public void setCustCatId(Integer custCatId) {
		this.custCatId = custCatId;
	}

	public Integer getVehClassId() {
		return vehClassId;
	}

	public void setVehClassId(Integer vehClassId) {
		this.vehClassId = vehClassId;
	}

	public float getDayrate() {
		return dayrate;
	}

	public void setDayrate(float dayrate) {
		this.dayrate = dayrate;
	}

	public float getAmoint() {
		return amoint;
	}

	public void setAmoint(float amoint) {
		this.amoint = amoint;
	}

	public LocalDateTime getRecorded() {
		return recorded;
	}

	public void setRecorded(LocalDateTime recorded) {
		this.recorded = recorded;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", hireId=" + hireId + ", custId=" + custId 
				+ ", vehId=" + vehId + ", dateStart=" + dateStart + ", dateEnd=" 
				+ dateEnd + ", custNric=" + custNric + ", custCatId=" + custCatId
				+ ", vehClassId=" + vehClassId + ", dayrate=" + dayrate 
				+ ", amoint=" + amoint + ", recorded=" + recorded + "]";
	}

}
