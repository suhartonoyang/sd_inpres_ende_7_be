// Generated with g9.

package co.id.sdinpresende7be.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "profile_type")
public class ProfileType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255, unique = true)
	private String name;
	@JsonIgnoreProperties(value = {"profileType"})
	@OneToMany(mappedBy = "profileType")
	private List<Profile> profiles;

	/** Default constructor. */
	public ProfileType() {
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
	 * Access method for profile.
	 *
	 * @return the current value of profile
	 */
	public List<Profile> getProfile() {
		return profiles;
	}

	/**
	 * Setter method for profile.
	 *
	 * @param aProfile the new value for profile
	 */
	public void setProfile(List<Profile> aProfiles) {
		profiles = aProfiles;
	}

	/**
	 * Compares the key for this instance with another ProfileType.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class ProfileType and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProfileType)) {
			return false;
		}
		ProfileType that = (ProfileType) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another ProfileType.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ProfileType))
			return false;
		return this.equalKeys(other) && ((ProfileType) other).equalKeys(this);
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
		return "ProfileType [id=" + id + ", name=" + name + ", profiles=" + profiles + "]";
	}

}
