package com.bms.util;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AccountUtil {
	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	private static final String DIGITS = "0123456789";
	private static final String SPECIAL_CHARS = "!@#$%&";
	private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARS;

	private final SecureRandom random = new SecureRandom();

	public String generateOtp() {
		Random random = new Random();
		int randomNumber = random.nextInt(999999);
		String output = Integer.toString(randomNumber);

		while (output.length() < 6) {
			output = "0" + output;
		}
		return output;
	}

	public String generateAccountNumber() {
		Random random = new Random();
		long accountNumber = 100000000000L + (long) (random.nextDouble() * 900000000000L);
		return Long.toString(accountNumber);
	}

	public String generateRandomPassword() {
		int length = 8;
		StringBuilder password = new StringBuilder(length);
		password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
		password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
		password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

		String combinedChars = ALL_CHARS;
		for (int i = 3; i < length; i++) {
			password.append(combinedChars.charAt(random.nextInt(combinedChars.length())));
		}

		return shuffleString(password.toString());
	}

	private String shuffleString(String input) {
		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			int randomIndex = random.nextInt(chars.length);
			char temp = chars[i];
			chars[i] = chars[randomIndex];
			chars[randomIndex] = temp;
		}
		return new String(chars);
	}

}
