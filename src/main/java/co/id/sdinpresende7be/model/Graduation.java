// Generated with g9.

package co.id.sdinpresende7be.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "graduation")
public class Graduation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, precision = 10)
	private int nis;
	@Column(unique = true, nullable = false, length = 255)
	private String name;
	@Column(nullable = false, length = 1)
	private String gender;
	@Column(nullable = false, precision = 10)
	private int year;
	@Column(name = "next_education", nullable = false, length = 255)
	private String nextEducation;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_by", length = 255)
	private String modifiedBy;
	@Column(name = "modified_date")
	private Date modifiedDate;

	/** Default constructor. */
	public Graduation() {
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
	 * Access method for nis.
	 *
	 * @return the current value of nis
	 */
	public int getNis() {
		return nis;
	}

	/**
	 * Setter method for nis.
	 *
	 * @param aNis the new value for nis
	 */
	public void setNis(int aNis) {
		nis = aNis;
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
	 * Access method for gender.
	 *
	 * @return the current value of gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter method for gender.
	 *
	 * @param aGender the new value for gender
	 */
	public void setGender(String aGender) {
		gender = aGender;
	}

	/**
	 * Access method for year.
	 *
	 * @return the current value of year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Setter method for year.
	 *
	 * @param aYear the new value for year
	 */
	public void setYear(int aYear) {
		year = aYear;
	}

	/**
	 * Access method for nextEducation.
	 *
	 * @return the current value of nextEducation
	 */
	public String getNextEducation() {
		return nextEducation;
	}

	/**
	 * Setter method for nextEducation.
	 *
	 * @param aNextEducation the new value for nextEducation
	 */
	public void setNextEducation(String aNextEducation) {
		nextEducation = aNextEducation;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Compares the key for this instance with another Graduation.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Graduation and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Graduation)) {
			return false;
		}
		Graduation that = (Graduation) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Graduation.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Graduation))
			return false;
		return this.equalKeys(other) && ((Graduation) other).equalKeys(this);
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
		return "Graduation [id=" + id + ", nis=" + nis + ", name=" + name + ", gender=" + gender + ", year=" + year + ", nextEducation="
				+ nextEducation + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}

}
