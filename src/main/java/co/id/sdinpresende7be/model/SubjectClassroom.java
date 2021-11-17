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
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "subject_classroom")
public class SubjectClassroom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String name;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@OneToMany(mappedBy = "subjectClassroom")
	@JsonIgnoreProperties(value = "subjectClassroom")
	private List<Enroll> enroll;
	@ManyToOne(optional = false)
	@JoinColumn(name = "teacher_id", nullable = false)
	@Where(clause = "role = teacher")
	@JsonIgnoreProperties(value = "subjectClassroom")
	private User teacher;
	@ManyToOne(optional = false)
	@JoinColumn(name = "classroom_id", nullable = false)
	@JsonIgnoreProperties(value = "subjectClassroom")
	private Classroom classroom;

	/** Default constructor. */
	public SubjectClassroom() {
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
	 * Access method for classroom.
	 *
	 * @return the current value of classroom
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * Setter method for classroom.
	 *
	 * @param aClassroom the new value for classroom
	 */
	public void setClassroom(Classroom aClassroom) {
		classroom = aClassroom;
	}

	/**
	 * Compares the key for this instance with another SubjectClassroom.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class SubjectClassroom and the
	 *         key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubjectClassroom)) {
			return false;
		}
		SubjectClassroom that = (SubjectClassroom) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another SubjectClassroom.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof SubjectClassroom))
			return false;
		return this.equalKeys(other) && ((SubjectClassroom) other).equalKeys(this);
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
		return "SubjectClassroom [id=" + id + ", name=" + name + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", enroll="
				+ enroll + ", teacher=" + teacher + ", classroom=" + classroom + "]";
	}

}
