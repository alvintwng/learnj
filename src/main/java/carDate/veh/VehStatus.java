package carDate.veh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VehStatus {
	
	@Id
	@Column(name = "VEHSTTSID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vehSttsId;
	
	@Column(length = 15, nullable = false, unique = true)
	private String name;

	/* 210308 to check is ready for company*/
	private boolean isActive;

	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public VehStatus() {
		super();
	}

	public Integer getVehSttsId() {
		return vehSttsId;
	}

	public void setVehSttsId(Integer vehSttsId) {
		this.vehSttsId = vehSttsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
/*
 INSERT INTO "SUMPROJ"."VEH_STATUS" (IS_ACTIVE, NAME) VALUES ('1', 'Live')

 * */
 