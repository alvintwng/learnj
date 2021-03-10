/* 210113B-BankApp/  */

package carDate.veh;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import carDate.hire.Hires;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VEHID")
	private long VehId;

	@Size(min = 1, max = 10)
	@NotNull
	private String vehModel;

	@Size(min = 3, max = 15)
	@NotNull
	private String vehBrand;

	@Column(unique = true)
	@Size(min = 3, max = 8)
	@NotNull
	private String vehLicPlate;

	/*DON'T USE! rate should at different table, shoud change to TYPE*/
	@Min(value = 10)
	@Max(value = 300)
	@NotNull
	private float dailyRate;

	/*//it just called the HireID, hired will reflected on status
	@ManyToOne
	@JoinColumn(name = "CURHIREID", nullable = true)
	private Hire currHire;
	 */
	
	@ManyToOne
	@JoinColumn(name = "VEHSTTSID", nullable = false)
	private VehStatus vehStatus;

//	// links this.Vehicle to Hires
//	@OneToMany
//	private Set<Hire> hires;
//	
//	public Set<Hire> getHires() {
//	return hires;
//}
//
//public void setHires(Set<Hire> hires) {
//	this.hires = hires;
//}

	public long getVehId() {
		return VehId;
	}

	public void setVehId(long vehId) {
		VehId = vehId;
	}

	public String getVehModel() {
		return vehModel;
	}

	public void setVehModel(String vehModel) {
		this.vehModel = vehModel;
	}

	public String getVehBrand() {
		return vehBrand;
	}

	public void setVehBrand(String vehBrand) {
		this.vehBrand = vehBrand;
	}

	public String getVehLicPlate() {
		return vehLicPlate;
	}

	public void setVehLicPlate(String vehLicPlate) {
		this.vehLicPlate = vehLicPlate;
	}

	public float getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(float dailyRate) {
		this.dailyRate = dailyRate;
	}

	public VehStatus getVehStatus() {
		return vehStatus;
	}

	public void setVehStatus(VehStatus vehStatus) {
		this.vehStatus = vehStatus;
	}

}
/*

INSERT INTO "SUMPROJ"."VEHICLE" (DAILY_RATE, VEH_BRAND, VEH_LIC_PLATE, VEH_MODEL, CURHIREID, VEHSTTSID)
 VALUES ('11', 'Vios', 'ABC123D', 'TOYOTA', '2', '1')

*/
 