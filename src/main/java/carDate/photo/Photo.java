package carDate.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Entity
//@Table(name="PHOTO")
public class Photo {
	@Id
	@Column(name = "photoid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String theType;
	private String name;

	@Column(nullable = true, length = 64)
	private String photo;
	
	@Transient
	public String getPhotosImagePath() {
		if (photo == null || id == null)
			return null;

		return "/images/" + photo;
	}

}
