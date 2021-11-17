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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "classroom", indexes = { @Index(name = "u_01", columnList = "name", unique = true) })
public class Classroom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(unique = true, nullable = false, length = 255)
	private String name;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_by", length = 255)
	private String modifiedBy;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@ManyToOne(optional = false)
	@JoinColumn(name = "teacher_id", nullable = false)
	@Where(clause = "role = TEACHER")
	@JsonIgnoreProperties(value = "classroom")
	private User teacher;
	@OneToMany(mappedBy = "classroom")
	@JsonIgnoreProperties(value = "classroom")
	private List<SubjectClassroom> subjectClassroom;

	/** Default constructor. */
	public Classroom() {
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
	 * Access method for subjectClassroom.
	 *
	 * @return the current value of subjectClassroom
	 */
	public List<SubjectClassroom> getSubjectClassroom() {
		return subjectClassroom;
	}

	/**
	 * Setter method for subjectClassroom.
	 *
	 * @param aSubjectClassroom the new value for subjectClassroom
	 */
	public void setSubjectClassroom(List<SubjectClassroom> aSubjectClassroom) {
		subjectClassroom = aSubjectClassroom;
	}

	/**
	 * Compares the key for this instance with another Classroom.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Classroom and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Classroom)) {
			return false;
		}
		Classroom that = (Classroom) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Classroom.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Classroom))
			return false;
		return this.equalKeys(other) && ((Classroom) other).equalKeys(this);
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
		return "Classroom [id=" + id + ", name=" + name + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", teacher=" + teacher + ", subjectClassroom=" + subjectClassroom + "]";
	}

}
