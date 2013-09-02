package se.jkrau.bukkit.secretword.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("restriction")
public class DatabaseUtils {
	
	/*
	 * Courtesy of OWASP
	 * 
	 * https://www.owasp.org/index.php/Hashing_Java
	 * 
	 */
	
	public static byte[] getSalt() {
		// Uses a secure Random not a simple Random
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Salt generation 64 bits long
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;
	}
	
	public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		byte[] bDigest = getHash(1000, password, salt);
		String sDigest = byteToBase64(bDigest);
		
		return sDigest;
	}
	
	public static byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}
	
	/**
	 * From a base 64 representation, returns the corresponding byte[]
	 * 
	 * @param data
	 *        String The base64 representation
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] base64ToByte(String data) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(data);
	}
	
	/**
	 * From a byte[] returns a base 64 representation
	 * 
	 * @param data
	 *        byte[]
	 * @return String
	 * @throws IOException
	 */
	public static String byteToBase64(byte[] data) {
		BASE64Encoder endecoder = new BASE64Encoder();
		return endecoder.encode(data);
	}
	
}
