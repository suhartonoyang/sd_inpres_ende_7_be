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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "aes_configuration")
public class AesConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "encrypt_algo", nullable = false, length = 255)
	private String encryptAlgo;
	@Column(name = "tag_length_byte", nullable = false, precision = 10)
	private int tagLengthByte;
	@Column(name = "iv_length_byte", nullable = false, precision = 10)
	private int ivLengthByte;
	@Column(name = "salt_length_byte", nullable = true, precision = 10)
	private int saltLengthByte;
	@Column(name = "aes_key_bit", nullable = true, precision = 10)
	private int aesKeyBit;
	@OneToMany(mappedBy = "aesConfiguration")
	@JsonIgnore
	private List<User> user;

	/** Default constructor. */
	public AesConfiguration() {
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
	 * Listter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(int aId) {
		id = aId;
	}

	/**
	 * Access method for encryptAlgo.
	 *
	 * @return the current value of encryptAlgo
	 */
	public String getEncryptAlgo() {
		return encryptAlgo;
	}

	/**
	 * Listter method for encryptAlgo.
	 *
	 * @param aEncryptAlgo the new value for encryptAlgo
	 */
	public void setEncryptAlgo(String aEncryptAlgo) {
		encryptAlgo = aEncryptAlgo;
	}

	/**
	 * Access method for tagLengthByte.
	 *
	 * @return the current value of tagLengthByte
	 */
	public int getTagLengthByte() {
		return tagLengthByte;
	}

	/**
	 * Listter method for tagLengthByte.
	 *
	 * @param aTagLengthByte the new value for tagLengthByte
	 */
	public void setTagLengthByte(int aTagLengthByte) {
		tagLengthByte = aTagLengthByte;
	}

	/**
	 * Access method for ivLengthByte.
	 *
	 * @return the current value of ivLengthByte
	 */
	public int getIvLengthByte() {
		return ivLengthByte;
	}

	/**
	 * Listter method for ivLengthByte.
	 *
	 * @param aIvLengthByte the new value for ivLengthByte
	 */
	public void setIvLengthByte(int aIvLengthByte) {
		ivLengthByte = aIvLengthByte;
	}

	/**
	 * Access method for saltLengthByte.
	 *
	 * @return the current value of saltLengthByte
	 */
	public int getSaltLengthByte() {
		return saltLengthByte;
	}

	/**
	 * Listter method for saltLengthByte.
	 *
	 * @param aSaltLengthByte the new value for saltLengthByte
	 */
	public void setSaltLengthByte(int aSaltLengthByte) {
		saltLengthByte = aSaltLengthByte;
	}

	public int getAesKeyBit() {
		return aesKeyBit;
	}

	public void setAesKeyBit(int aesKeyBit) {
		this.aesKeyBit = aesKeyBit;
	}

	/**
	 * Access method for user.
	 *
	 * @return the current value of user
	 */
	public List<User> getUser() {
		return user;
	}

	/**
	 * Listter method for user.
	 *
	 * @param aUser the new value for user
	 */
	public void setUser(List<User> aUser) {
		user = aUser;
	}

	/**
	 * Compares the key for this instance with another AesConfiguration.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class AesConfiguration and the
	 *         key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AesConfiguration)) {
			return false;
		}
		AesConfiguration that = (AesConfiguration) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another AesConfiguration.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof AesConfiguration))
			return false;
		return this.equalKeys(other) && ((AesConfiguration) other).equalKeys(this);
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
		return "AesConfiguration [id=" + id + ", encryptAlgo=" + encryptAlgo + ", tagLengthByte=" + tagLengthByte + ", ivLengthByte="
				+ ivLengthByte + ", saltLengthByte=" + saltLengthByte + ", user=" + user + "]";
	}

}
