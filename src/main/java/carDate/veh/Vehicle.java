/* 210113B-BankApp/  */

package carDate.veh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VEHID")
	private long VehId;
	
	@Size (min = 8, max = 25)
	@NotNull
	private String vehModel;
	
	@Size (min = 8, max = 25)
	@NotNull
	private String vehBrand;
	
	@Column(unique=true)
	@Size (min = 3, max = 8)
	@NotNull
	private String vehLicPlate;
	
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

}
