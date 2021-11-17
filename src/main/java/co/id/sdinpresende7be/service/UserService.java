package co.id.sdinpresende7be.service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.id.sdinpresende7be.model.AesConfiguration;
import co.id.sdinpresende7be.model.User;
import co.id.sdinpresende7be.repo.UserRepo;
import co.id.sdinpresende7be.utils.EncryptorAesGcmPassword;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AesConfigurationService aesConfigurationService;

	@Autowired
	private EncryptorAesGcmPassword encryptor;

	@Value("${password.key.aes}")
	private String PASSWORD_KEY;

	private final Charset UTF_8 = StandardCharsets.UTF_8;

	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public User register(User user) throws Exception {
		User newUser = new User();
		User existingUser = getUserById(user.getId());
		if (existingUser == null) {
			newUser.setCreatedBy(user.getCreatedBy());
			newUser.setCreatedDate(new Date());
		} else {
			newUser.setId(existingUser.getId());
			newUser.setCreatedBy(existingUser.getCreatedBy());
			newUser.setCreatedDate(existingUser.getCreatedDate());
			newUser.setModifiedBy(user.getCreatedBy());
			newUser.setModifiedDate(new Date());
		}

		AesConfiguration aesConfiguration = aesConfigurationService.getAesConfigurationById(1);
		String encryptedPassword = encryptor.encrypt(user.getPassword().getBytes(UTF_8), PASSWORD_KEY, aesConfiguration);

		newUser.setUsername(user.getUsername());
		newUser.setPassword(encryptedPassword);
		newUser.setPasswordKey(PASSWORD_KEY);
		newUser.setAesConfiguration(aesConfiguration);
		newUser.setRole(user.getRole().toUpperCase());
		newUser.setName(user.getName());

		return userRepo.save(newUser);
	}

	public User login(String username, String password) throws Exception {
		User user = getUserByUsername(username);
		if (user == null)
			return null;

		if (!PASSWORD_KEY.equals(user.getPasswordKey()))
			throw new Exception("Password Key in server and database are not match");

		AesConfiguration aesConfiguration = user.getAesConfiguration();
		String decryptedText = encryptor.decrypt(user.getPassword(), PASSWORD_KEY, aesConfiguration);

		if (decryptedText == null || decryptedText.equals(""))
			throw new Exception("Result of decrypt password is empty");
		else if (!decryptedText.equals(password))
			return null;

		return user;
	}

	public List<User> getAllTeachers() {
		return userRepo.findByRole("teacher");
	}

	public User getUserById(Integer id) {
		return userRepo.findById(id).orElse(null);
	}

	public void deleteUserById(Integer id) {
		userRepo.deleteById(id);
	}

}
