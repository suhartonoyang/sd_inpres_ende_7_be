// Generated with g9.

package co.id.sdinpresende7be.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "gallery")
public class Gallery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String title;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_by", length = 255)
	private String modifiedBy;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "file_id")
	@JsonIgnore
	private File file;

	@Transient
	private String imageUrl;

	public String getImageUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/galleries/" + this.id + "/image").toUriString();
	}

	/** Default constructor. */
	public Gallery() {
		super();
	}

	/**
	 * Access method for id.
	 *
	 * @return the current value of id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(int aId) {
		id = aId;
	}

	/**
	 * Access method for title.
	 *
	 * @return the current value of title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter method for title.
	 *
	 * @param aTitle the new value for title
	 */
	public void setTitle(String aTitle) {
		title = aTitle;
	}

	/**
	 * Access method for createdBy.
	 *
	 * @return the current value of createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Setter method for createdBy.
	 *
	 * @param aCreatedBy the new value for createdBy
	 */
	public void setCreatedBy(String aCreatedBy) {
		createdBy = aCreatedBy;
	}

	/**
	 * Access method for createdDate.
	 *
	 * @return the current value of createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Setter method for createdDate.
	 *
	 * @param aCreatedDate the new value for createdDate
	 */
	public void setCreatedDate(Date aCreatedDate) {
		createdDate = aCreatedDate;
	}

	/**
	 * Access method for modifiedBy.
	 *
	 * @return the current value of modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Setter method for modifiedBy.
	 *
	 * @param aModifiedBy the new value for modifiedBy
	 */
	public void setModifiedBy(String aModifiedBy) {
		modifiedBy = aModifiedBy;
	}

	/**
	 * Access method for modifiedDate.
	 *
	 * @return the current value of modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Setter method for modifiedDate.
	 *
	 * @param aModifiedDate the new value for modifiedDate
	 */
	public void setModifiedDate(Date aModifiedDate) {
		modifiedDate = aModifiedDate;
	}

	/**
	 * Compares the key for this instance with another Gallery.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Gallery and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Gallery)) {
			return false;
		}
		Gallery that = (Gallery) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Gallery.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Gallery))
			return false;
		return this.equalKeys(other) && ((Gallery) other).equalKeys(this);
	}

	/**
	 * Returns a hash code for this instance.
	 *
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		int i;
		int result = 17;
		i = getId();
		result = 37 * result + i;
		return result;
	}

	@Override
	public String toString() {
		return "Gallery [id=" + id + ", title=" + title + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", file=" + file + ", imageUrl=" + imageUrl + "]";
	}

}
