package carDate.inv;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INVID")
	private int invId;

    @OneToOne
    @JoinColumn(name = "invpaymtid")
	private InvPaymt invPaymt;
    
	@Column(name="invno", unique=true)
	@Size(min = 4, max = 20)
	private String invNo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dated")
	private LocalDate dated;

	@Column(name="custid")
	private int custId;
	
	@Column(name="hireid")
	private int hireId;	
	
	@Size(max =65)
	private String desc1;
	
	@Size(max =65)
	private String desc2;
	
	@Size(max =65)
	private String desc3;

	private float rated;

	@Column(name="paymtdone")
	private boolean paymtDone;

	public Invoice() {
		super();
	}

	@Override
	public String toString() {
		return "Invoice [invId=" + invId + ", invpaymt=" + invPaymt + ", invNo=" + invNo + ", dated=" + dated
				+ ", custId=" + custId + ", hireId=" + hireId + ", rated=" + rated + "]";
	}

	public int getInvId() {
		return invId;
	}


	public void setPaidDate(LocalDate paidDate) {
		this.invPaymt.setPaidDate(paidDate);
	}
	public LocalDate getPaidDate() {
		return this.invPaymt.getPaidDate();
	}

	public InvPaymt getInvPaymt() {
		return invPaymt;
	}

	public void setInvPaymt(InvPaymt invPaymt) {
		this.invPaymt = invPaymt;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public LocalDate getDated() {
		return dated;
	}

	public void setDated(LocalDate dated) {
		this.dated = dated;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getHireId() {
		return hireId;
	}

	public void setHireId(int hireId) {
		this.hireId = hireId;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public float getRated() {
		return rated;
	}

	public void setRated(float rated) {
		this.rated = rated;
	}

	public boolean isPaymtDone() {
		return paymtDone;
	}

	public void setPaymtDone(boolean paymtDone) {
		this.paymtDone = paymtDone;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

}