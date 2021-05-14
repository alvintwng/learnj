/*210208B-ManyToMany*/
package carDate.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PHOTODETAIL")
public class PhotoDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 45)
	private String name;
	
	@Column(length = 45)
	private String value;
	
	@ManyToOne
	@JoinColumn(name="PHOTOID")
	private Photo photoRef;
	
	public PhotoDetail() {
	}

	public PhotoDetail(Integer id, String name, String value, Photo photo) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.photoRef = photo;
	}

	public PhotoDetail(String name, String value, Photo photo) {
		this.name = name;
		this.value = value;
		this.photoRef = photo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Photo getPhoto() {
		return photoRef;
	}

	public void setPhoto(Photo photo) {
		this.photoRef = photo;
	}
	
	@Override
	public String toString() {
		return name+ " : " + value;
	}
}
