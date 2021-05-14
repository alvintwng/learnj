package carDate.photo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="PHOTO")
public class Photo {
	@Id
	@Column(name = "photoid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;
	
	private Integer param; // not use

	@Column(nullable = true, length = 64)
	private String photo;
	
	@OneToMany(mappedBy = "photoRef", cascade = CascadeType.ALL)
	private List<PhotoDetail> details = new ArrayList<>();
	
	@Transient
	public String getPhotosImagePath() {
		if (photo == null || photoId == null)
			return null;

		return "/photos/" + photo;
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<PhotoDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PhotoDetail> details) {
		this.details = details;
	}
	public void setDetails(Integer id, String name, String value) {
		this.details.add(new PhotoDetail(id, name, value, this));
	}
	public void addDetails(String name, String value) {
		this.details.add(new PhotoDetail(name, value, this));
	}

	@Override
	public String toString() {
		return "Photo [photoId=" + photoId + ", param=" + param + ", photo=" + photo + ", details=" + details + "]";
	}
}
