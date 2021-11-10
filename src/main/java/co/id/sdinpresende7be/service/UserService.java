package co.id.sdinpresende7be.service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

	public User register(String username, String password) throws Exception {
		AesConfiguration aesConfiguration = aesConfigurationService.getAesConfigurationById(1);
		String encryptedPassword = encryptor.encrypt(password.getBytes(UTF_8), PASSWORD_KEY, aesConfiguration);
		System.out.println("encryptedPassword: " + encryptedPassword);
		User user = new User();
		user.setCreatedBy("Admin");
		user.setCreatedDate(new Date());
		user.setUsername(username);
		user.setPassword(encryptedPassword);
		user.setPasswordKey(PASSWORD_KEY);
		user.setAesConfiguration(aesConfiguration);
		return userRepo.save(user);
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

}
