package carDate.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dailyrate")
public class DailyRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int id;
	
	@Column(name="vehclassid", nullable = false, unique = true)
	private long vehClassId;
	
	@Column(name="custcatid")
	private long custCatId;

	private float dayrate;
	
	public DailyRate() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCustCatId() {
		return custCatId;
	}

	public void setCustCatId(long custCatId) {
		this.custCatId = custCatId;
	}

	public long getVehClassId() {
		return vehClassId;
	}

	public void setVehClassId(long vehClassId) {
		this.vehClassId = vehClassId;
	}

	public float getDayrate() {
		return dayrate;
	}

	public void setDayrate(float dayrate) {
		this.dayrate = dayrate;
	}

	@Override
	public String toString() {
		return "DailyRate [id=" + id + ", custCatId=" + custCatId 
				+ ", vehClassId=" + vehClassId + ", dayrate="
				+ dayrate + "]";
	}

}
