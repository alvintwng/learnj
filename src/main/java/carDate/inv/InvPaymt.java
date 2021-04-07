package carDate.inv;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invpaymt")
public class InvPaymt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invPaymtId;

	//@OneToOne
	//@JoinColumn(name = "invid")
	//private Invoice invoice;
    private int invid;
    
	@Column(name = "paiddate")
	private LocalDate paidDate;
	
	private float amount;
	
	private boolean archive;

	private int entity;

	public InvPaymt() {
		super();
	}

	@Override
	public String toString() {
		return "InvPaymt [invPaymtId=" + invPaymtId + ", paidDate=" + paidDate 
				+ ", amount=" + amount + ", archive=" + archive + ", entity=" + entity + "]";
	}

	public int getInvPaymtId() {
		return invPaymtId;
	}

	public void setInvPaymtId(int invPaymtId) {
		this.invPaymtId = invPaymtId;
	}

	public int getInvid() {
		return invid;
	}

	public void setInvid(int invid) {
		this.invid = invid;
	}

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}
}
