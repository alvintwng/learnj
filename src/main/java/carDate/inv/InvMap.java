package carDate.inv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invmap")
public class InvMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "invid")
	private int invId;
	
	@Column(name = "custnameid")
	private long custNameId;
	
	@Column(name = "hireid")
	private long hireId;

	public InvMap() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public long getCustNameId() {
		return custNameId;
	}

	public void setCustNameId(long custNameId) {
		this.custNameId = custNameId;
	}

	public long getHireId() {
		return hireId;
	}

	public void setHireId(long hireId) {
		this.hireId = hireId;
	}

	@Override
	public String toString() {
		return "InvMap [id=" + id + ", invId=" + invId + ", custNameId=" + custNameId + ", hireId=" + hireId + "]";
	}

}
