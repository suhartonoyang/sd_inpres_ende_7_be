// Generated with g9.

package co.id.sdinpresende7be.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "criticsm_suggestion")
public class CriticsmSuggestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String name;
	@Column(nullable = false, length = 255)
	private String email;
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phoneNumber;
	@Column(nullable = false, length = 255)
	private String description;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_by", length = 255)
	private String modifiedBy;
	@Column(name = "modified_date")
	private Date modifiedDate;

	/** Default constructor. */
	public CriticsmSuggestion() {
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
	 * Access method for name.
	 *
	 * @return the current value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for name.
	 *
	 * @param aName the new value for name
	 */
	public void setName(String aName) {
		name = aName;
	}

	/**
	 * Access method for email.
	 *
	 * @return the current value of email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter method for email.
	 *
	 * @param aEmail the new value for email
	 */
	public void setEmail(String aEmail) {
		email = aEmail;
	}

	/**
	 * Access method for phoneNumber.
	 *
	 * @return the current value of phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Setter method for phoneNumber.
	 *
	 * @param aPhoneNumber the new value for phoneNumber
	 */
	public void setPhoneNumber(String aPhoneNumber) {
		phoneNumber = aPhoneNumber;
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
	 * Compares the key for this instance with another CriticsmSuggestion.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class CriticsmSuggestion and the
	 *         key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CriticsmSuggestion)) {
			return false;
		}
		CriticsmSuggestion that = (CriticsmSuggestion) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another CriticsmSuggestion.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof CriticsmSuggestion))
			return false;
		return this.equalKeys(other) && ((CriticsmSuggestion) other).equalKeys(this);
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
		return "CriticsmSuggestion [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", description="
				+ description + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
