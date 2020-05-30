package fr.biblioc.bibliocauthentification.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe de sécurisation des mots de passe
 */
public class PasswordDigest {

    /**
     * Méthode permettant de hash et saler un String
     * @param password string
     * @return string hash
     */
    public static String hashAndSalt(String password){

        String cle_salage = password.length()+"Agrougrou";
        password += cle_salage;

        MessageDigest vMessageDigest = null;
        try {
            vMessageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashInBytes = vMessageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

        // bytes to hex
        StringBuilder vStringBuilder = new StringBuilder();
        for (byte b : hashInBytes) {
            vStringBuilder.append(String.format("%02x", b));
        }

        return vStringBuilder.toString();
    }
}
