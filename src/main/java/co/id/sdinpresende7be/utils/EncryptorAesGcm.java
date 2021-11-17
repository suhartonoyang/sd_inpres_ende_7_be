package co.id.sdinpresende7be.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * AES-GCM inputs - 12 bytes IV, need the same IV and secret keys for encryption and decryption.
 * <p>
 * The output consist of iv, encrypted content, and auth tag in the following format:
 * output = byte[] {i i i c c c c c c ...}
 * <p>
 * i = IV bytes
 * c = content bytes (encrypted content, auth tag)
 */

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.id.sdinpresende7be.model.AesConfiguration;
import co.id.sdinpresende7be.service.AesConfigurationService;

@Component
public class EncryptorAesGcm {

	@Autowired
	private AesConfigurationService aesConfigurationService;

	public void encrypt(File inputFile, File outputFile) throws Exception {
		System.out.println("start encrypt file");
		AesConfiguration aesConfiguration = aesConfigurationService.getAesConfigurationById(3);
//		SecretKey secretKey = CryptoUtils.getAESKey(aesConfiguration.getAesKeyBit());
//		byte[] iv = CryptoUtils.getRandomNonce(aesConfiguration.getIvLengthByte());
		String key = "Mary has one cat";
		Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
//		cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(aesConfiguration.getTagLengthByte(), iv));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		System.out.println("create inputStream");
		FileInputStream inputStream = new FileInputStream(inputFile);
		System.out.println("create inputBytes");
		byte[] inputBytes = new byte[(int) inputFile.length()];
		System.out.println("write inputBytes");
		inputStream.read(inputBytes);

		System.out.println("create outputBytes");
		byte[] outputBytes = cipher.doFinal(inputBytes);
//		byte[] outputBytesWithIV = ByteBuffer.allocate(iv.length + outputBytes.length).put(iv).put(outputBytes).array();

		System.out.println("create outputStream");
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		System.out.println("write outputStream");
		outputStream.write(outputBytes);

		inputStream.close();
		outputStream.close();
	}

	public void decrypt(File inputFile, File outputFile) throws Exception {
		System.out.println("start decrypt file");

		

		AesConfiguration aesConfiguration = aesConfigurationService.getAesConfigurationById(3);

//		System.out.println("process byte buffer");
//		ByteBuffer bb = ByteBuffer.wrap(inputBytes);
//		byte[] iv = new byte[aesConfiguration.getIvLengthByte()];
//		bb.get(iv);
//		byte[] cipherText = new byte[bb.remaining()];
//		bb.get(cipherText);

		System.out.println("process chiper");
		String key = "Mary has one cat";
		Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
//		SecretKey secretKey = CryptoUtils.getAESKey(aesConfiguration.getAesKeyBit());
//		cipher.init(Cipher.DECRYPT_MODE, secretKey,
//				new GCMParameterSpec(aesConfiguration.getTagLengthByte(), iv));
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		System.out.println("create inputStream");
		FileInputStream inputStream = new FileInputStream(inputFile);
		System.out.println("create inputBytes");
		byte[] inputBytes = new byte[(int) inputFile.length()];
		System.out.println("write inputBytes");
		inputStream.read(inputBytes);
		System.out.println("create outputBytes");
		byte[] outputBytes = cipher.doFinal(inputBytes);

		System.out.println("create outputStream");
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		System.out.println("write outputStream");
		outputStream.write(outputBytes);

		inputStream.close();
		outputStream.close();

	}
}