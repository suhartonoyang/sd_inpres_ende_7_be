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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user", indexes = { @Index(name = "user_idx01", columnList = "username", unique = true) })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(unique = true, nullable = false, length = 255)
	private String username;
	@Column(nullable = false, length = 255)
	private String password;
	@Column(length = 255)
	private String name;
	@Column(nullable = false, length = 255)
	private String role;
	@Column(name = "password_key", nullable = false, length = 255)
	private String passwordKey;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_by", length = 255)
	private String modifiedBy;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@OneToMany(mappedBy = "teacher")
	@JsonIgnoreProperties(value = "user")
	@JsonIgnore
	private List<Classroom> classroom;
	@OneToMany(mappedBy = "teacher")
	@JsonIgnoreProperties(value = "teacher")
	@JsonIgnore
	private List<Student> student;
	@OneToMany(mappedBy = "teacher")
	@JsonIgnoreProperties(value = "teacher")
	@JsonIgnore
	private List<SubjectClassroom> subjectClassroom;
	@ManyToOne(optional = false)
	@JoinColumn(name = "aes_configuration_id", nullable = false)
	@JsonIgnoreProperties(value = "user")
	private AesConfiguration aesConfiguration;

	/** Default constructor. */
	public User() {
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
	 * Access method for username.
	 *
	 * @return the current value of username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter method for username.
	 *
	 * @param aUsername the new value for username
	 */
	public void setUsername(String aUsername) {
		username = aUsername;
	}

	/**
	 * Access method for password.
	 *
	 * @return the current value of password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for password.
	 *
	 * @param aPassword the new value for password
	 */
	public void setPassword(String aPassword) {
		password = aPassword;
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
	 * Access method for role.
	 *
	 * @return the current value of role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Setter method for role.
	 *
	 * @param aRole the new value for role
	 */
	public void setRole(String aRole) {
		role = aRole;
	}

	/**
	 * Access method for passwordKey.
	 *
	 * @return the current value of passwordKey
	 */
	public String getPasswordKey() {
		return passwordKey;
	}

	/**
	 * Setter method for passwordKey.
	 *
	 * @param aPasswordKey the new value for passwordKey
	 */
	public void setPasswordKey(String aPasswordKey) {
		passwordKey = aPasswordKey;
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
	 * Access method for classroom.
	 *
	 * @return the current value of classroom
	 */
	public List<Classroom> getClassroom() {
		return classroom;
	}

	/**
	 * Setter method for classroom.
	 *
	 * @param aClassroom the new value for classroom
	 */
	public void setClassroom(List<Classroom> aClassroom) {
		classroom = aClassroom;
	}

	/**
	 * Access method for student.
	 *
	 * @return the current value of student
	 */
	public List<Student> getStudent() {
		return student;
	}

	/**
	 * Setter method for student.
	 *
	 * @param aStudent the new value for student
	 */
	public void setStudent(List<Student> aStudent) {
		student = aStudent;
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
	 * Access method for aesConfiguration.
	 *
	 * @return the current value of aesConfiguration
	 */
	public AesConfiguration getAesConfiguration() {
		return aesConfiguration;
	}

	/**
	 * Setter method for aesConfiguration.
	 *
	 * @param aAesConfiguration the new value for aesConfiguration
	 */
	public void setAesConfiguration(AesConfiguration aAesConfiguration) {
		aesConfiguration = aAesConfiguration;
	}

	/**
	 * Compares the key for this instance with another User.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class User and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof User)) {
			return false;
		}
		User that = (User) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another User.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof User))
			return false;
		return this.equalKeys(other) && ((User) other).equalKeys(this);
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", role=" + role
				+ ", passwordKey=" + passwordKey + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", classroom=" + classroom + ", student=" + student
				+ ", subjectClassroom=" + subjectClassroom + ", aesConfiguration=" + aesConfiguration + "]";
	}

}
