package card_ops_app.card_ops_api.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {

	// Temperory put hardcode
	private static final String SECRET_KEY = "MySecretKey12345";

	public static String encrypt(String data) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new RuntimeException("Error encrypting", e);
		}
	}

	public static String decrypt(String encryptedData) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
		} catch (Exception e) {
			throw new RuntimeException("Error decrypting", e);
		}
	}
}
