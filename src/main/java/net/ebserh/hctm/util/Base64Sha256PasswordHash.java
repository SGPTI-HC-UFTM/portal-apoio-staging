package net.ebserh.hctm.util;

import jakarta.enterprise.context.Dependent;
import jakarta.security.enterprise.identitystore.PasswordHash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Dependent
public class Base64Sha256PasswordHash implements PasswordHash {

    @Override
    public String generate(char[] password) {
        try {
            byte[] bytes = new String(password).getBytes(StandardCharsets.UTF_8);
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            digester.update(bytes);

            return Base64.getEncoder().encodeToString(digester.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.");
        }
    }

    @Override
    public boolean verify(char[] password, String expectedHash) {
        try {
            byte[] bytes = new String(password).getBytes(StandardCharsets.UTF_8);
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            digester.update(bytes);
            String hashedPassword = Base64.getEncoder().encodeToString(digester.digest());

            return hashedPassword.equals(expectedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.");
        }
    }
}
