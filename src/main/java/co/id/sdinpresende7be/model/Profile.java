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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "profile", indexes = { @Index(name = "profile_profile_type_IX", columnList = "profile_type", unique = true) })
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "profile_type", unique = true, nullable = false, length = 255)
	private String profileType;
	@Column(length = 255)
	private String title;
	private String description;
	private String vision;
	private String mission;
	@Column(length = 255)
	private String category;
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
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/profiles/" + this.profileType + "/image").toUriString();
	}

	/** Default constructor. */
	public Profile() {
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
	 * Access method for profileType.
	 *
	 * @return the current value of profileType
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 * Setter method for profileType.
	 *
	 * @param aProfileType the new value for profileType
	 */
	public void setProfileType(String aProfileType) {
		profileType = aProfileType;
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
	 * Access method for description.
	 *
	 * @return the current value of description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter method for description.
	 *
	 * @param aDescription the new value for description
	 */
	public void setDescription(String aDescription) {
		description = aDescription;
	}

	/**
	 * Access method for vision.
	 *
	 * @return the current value of vision
	 */
	public String getVision() {
		return vision;
	}

	/**
	 * Setter method for vision.
	 *
	 * @param aVision the new value for vision
	 */
	public void setVision(String aVision) {
		vision = aVision;
	}

	/**
	 * Access method for mission.
	 *
	 * @return the current value of mission
	 */
	public String getMission() {
		return mission;
	}

	/**
	 * Setter method for mission.
	 *
	 * @param aMission the new value for mission
	 */
	public void setMission(String aMission) {
		mission = aMission;
	}

	/**
	 * Access method for category.
	 *
	 * @return the current value of category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setter method for category.
	 *
	 * @param aCategory the new value for category
	 */
	public void setCategory(String aCategory) {
		category = aCategory;
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
	 * Compares the key for this instance with another Profile.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Profile and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Profile)) {
			return false;
		}
		Profile that = (Profile) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Profile.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Profile))
			return false;
		return this.equalKeys(other) && ((Profile) other).equalKeys(this);
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
		return "Profile [id=" + id + ", profileType=" + profileType + ", title=" + title + ", description=" + description + ", vision="
				+ vision + ", mission=" + mission + ", category=" + category + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", file=" + file + "]";
	}

}
