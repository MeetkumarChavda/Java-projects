package Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * The `SecurityHandler` class provides methods for secure password handling and
 * authentication.
 * It generates random salts, hashes passwords using SHA-256, and verifies
 * password authenticity.
 */
public class SecurityHandler {

    private final byte[] salt;

    /**
     * Initializes a new `SecurityHandler` instance with a randomly generated salt.
     *
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public SecurityHandler() throws NoSuchAlgorithmException {
        this.salt = generateSalt();
    }

    /**
     * Gets the salt associated with this `SecurityHandler` instance.
     * 
     * @return The salt as a byte array.
     */
    protected byte[] getSalt() {
        return salt;
    }

    /**
     * Generates a hashed password based on the provided password and the instance's
     * salt.
     *
     * @param password The password to hash.
     * @return The hashed password as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public String generateHashPassword(String password) throws NoSuchAlgorithmException {
        return hashPassword(password, salt);
    }

    /**
     * Verifies the authenticity of a password by hashing the entered password and
     * comparing it to the stored hashed password.
     *
     * @param enteredPassword      The entered password to verify.
     * @param userSalt             The salt associated with the user's password.
     * @param storedHashedPassword The stored hashed password to compare against.
     * @return `true` if the entered password matches the stored hashed password,
     *         otherwise `false`.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */

    public boolean authenticateUser(String check, byte[] salt, String HPWD) throws NoSuchAlgorithmException {
        return verifyPassword(check, salt, HPWD);
    }

    // Private Methods//

    /**
     * Generates a random salt as a byte array.
     *
     * @return The generated salt.
     */
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Hashes a password using the SHA-256 algorithm and a given salt.
     *
     * @param password The password to hash.
     * @param salt     The salt used for hashing.
     * @return The hashed password as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return byteArrayToHex(salt) + byteArrayToHex(hashedPassword);
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param bytes The byte array to convert.
     * @return The hexadecimal representation of the byte array.
     */
    private static String byteArrayToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    /**
     * Verifies the authenticity of a password by hashing the entered password and
     * comparing it to the stored hashed password.
     *
     * @param enteredPassword      The entered password to verify.
     * @param userSalt             The salt associated with the user's password.
     * @param storedHashedPassword The stored hashed password to compare against.
     * @return `true` if the entered password matches the stored hashed password,
     *         otherwise `false`.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    private boolean verifyPassword(String enteredPassword, byte[] Usersalt, String storedHashedPassword)
            throws NoSuchAlgorithmException {
        String hashedEnteredPassword = hashPassword(enteredPassword, Usersalt);
        return hashedEnteredPassword.equals(storedHashedPassword);
    }

}
