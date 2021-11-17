// Generated with g9.

package co.id.sdinpresende7be.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(unique = true, nullable = false, precision = 10)
	private int nis;
	@Column(nullable = false, length = 255)
	private String name;
	@Column(nullable = false, length = 1)
	private String gender;
	@Column(nullable = false, length = 255)
	private String guardian;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_by", length = 255)
	private String modifiedBy;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@OneToMany(mappedBy = "student")
	@JsonIgnoreProperties(value = { "student" })
	private List<Enroll> enroll;
	@ManyToOne(optional = false)
	@JoinColumn(name = "teacher_id", nullable = false)
	@Where(clause = "role = TEACHER")
	@JsonIgnoreProperties(value = { "student" })
	private User teacher;

	/** Default constructor. */
	public Student() {
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
	 * Access method for guardian.
	 *
	 * @return the current value of guardian
	 */
	public String getGuardian() {
		return guardian;
	}

	/**
	 * Setter method for guardian.
	 *
	 * @param aGuardian the new value for guardian
	 */
	public void setGuardian(String aGuardian) {
		guardian = aGuardian;
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
	 * Access method for enroll.
	 *
	 * @return the current value of enroll
	 */
	public List<Enroll> getEnroll() {
		return enroll;
	}

	/**
	 * Setter method for enroll.
	 *
	 * @param aEnroll the new value for enroll
	 */
	public void setEnroll(List<Enroll> aEnroll) {
		enroll = aEnroll;
	}

	/**
	 * Access method for user.
	 *
	 * @return the current value of user
	 */
	public User getTeacher() {
		return teacher;
	}

	/**
	 * Setter method for user.
	 *
	 * @param aUser the new value for user
	 */
	public void setTeacher(User aTeacher) {
		teacher = aTeacher;
	}

	/**
	 * Compares the key for this instance with another Student.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Student and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Student)) {
			return false;
		}
		Student that = (Student) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Student.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Student))
			return false;
		return this.equalKeys(other) && ((Student) other).equalKeys(this);
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
		return "Student [id=" + id + ", nis=" + nis + ", name=" + name + ", gender=" + gender + ", guardian=" + guardian + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", enroll=" + enroll + ", teacher=" + teacher + "]";
	}

}
