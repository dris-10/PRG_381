package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/*
 * Handles password hashing and verification using SHA-256 with a random salt.
 */
public class PasswordUtil {

    private static final int SALT_LENGTH = 16;

    // Hashes a plain password, returning "salt:hash" (both Base64 encoded)
    public static String hashPassword(String plainPassword) {

        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);

        String hash = sha256(plainPassword, salt);

        return Base64.getEncoder().encodeToString(salt) + ":" + hash;
    }

    // Checks a plain password against a stored "salt:hash" value
    public static boolean verifyPassword(String plainPassword, String storedValue) {

        if (plainPassword == null || storedValue == null || !storedValue.contains(":")) {
            return false;
        }

        String[] parts = storedValue.split(":", 2);
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        String expectedHash = parts[1];

        return sha256(plainPassword, salt).equals(expectedHash);
    }

    private static String sha256(String plainPassword, byte[] salt) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);

            byte[] hashedBytes = digest.digest(plainPassword.getBytes());

            return Base64.getEncoder().encodeToString(hashedBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}
