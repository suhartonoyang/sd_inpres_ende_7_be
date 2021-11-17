// Generated with g9.

package co.id.sdinpresende7be.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "enroll")
public class Enroll implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "subject_classroom_id", nullable = false)
	@JsonIgnoreProperties(value = "enroll")
	private SubjectClassroom subjectClassroom;
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id", nullable = false)
	@JsonIgnoreProperties(value = "enroll")
	private Student student;

	@Column(nullable = false, precision = 10)
	private double score;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	/** Default constructor. */
	public Enroll() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Access method for score.
	 *
	 * @return the current value of score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Setter method for score.
	 *
	 * @param aScore the new value for score
	 */
	public void setScore(double aScore) {
		score = aScore;
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
	 * Access method for subjectClassroom.
	 *
	 * @return the current value of subjectClassroom
	 */
	public SubjectClassroom getSubjectClassroom() {
		return subjectClassroom;
	}

	/**
	 * Setter method for subjectClassroom.
	 *
	 * @param aSubjectClassroom the new value for subjectClassroom
	 */
	public void setSubjectClassroom(SubjectClassroom aSubjectClassroom) {
		subjectClassroom = aSubjectClassroom;
	}

	/**
	 * Access method for student.
	 *
	 * @return the current value of student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Setter method for student.
	 *
	 * @param aStudent the new value for student
	 */
	public void setStudent(Student aStudent) {
		student = aStudent;
	}

	@Override
	public String toString() {
		return "Enroll [enrollId=" + id + ", subjectClassroom=" + subjectClassroom + ", student=" + student + ", score=" + score
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}

}
