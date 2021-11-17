// Generated with g9.

package co.id.sdinpresende7be.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity(name = "file")
public class File implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String name;
	@Column(nullable = false, length = 255)
	private String type;
	@Column(nullable = false, length = 16777215)
	private byte[] data;
	@OneToMany(mappedBy = "file")
	@JsonIgnoreProperties(value = "file")
	private List<Event> event;
	@OneToMany(mappedBy = "file")
	@JsonIgnoreProperties(value = "file")
	private List<Gallery> gallery;
	@OneToMany(mappedBy = "file")
	@JsonIgnoreProperties(value = "file")
	private List<News> news;
	@OneToMany(mappedBy = "file")
	@JsonIgnoreProperties(value = "file")
	private List<Profile> profile;

	/** Default constructor. */
	public File() {
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
	 * Access method for type.
	 *
	 * @return the current value of type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter method for type.
	 *
	 * @param aType the new value for type
	 */
	public void setType(String aType) {
		type = aType;
	}

	/**
	 * Access method for data.
	 *
	 * @return the current value of data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Setter method for data.
	 *
	 * @param aData the new value for data
	 */
	public void setData(byte[] aData) {
		data = aData;
	}

	/**
	 * Access method for event.
	 *
	 * @return the current value of event
	 */
	public List<Event> getEvent() {
		return event;
	}

	/**
	 * Setter method for event.
	 *
	 * @param aEvent the new value for event
	 */
	public void setEvent(List<Event> aEvent) {
		event = aEvent;
	}

	/**
	 * Compares the key for this instance with another File.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class File and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof File)) {
			return false;
		}
		File that = (File) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another File.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof File))
			return false;
		return this.equalKeys(other) && ((File) other).equalKeys(this);
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
		return "File [id=" + id + ", name=" + name + ", type=" + type + ", data=" + Arrays.toString(data) + ", event=" + event + "]";
	}

}
