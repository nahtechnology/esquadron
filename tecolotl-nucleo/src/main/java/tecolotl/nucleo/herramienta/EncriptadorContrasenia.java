package tecolotl.nucleo.herramienta;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Herramienta para encriptar las contraseñas
 * de los usuarios.
 * @version  1.0
 * @author Jesus Caballero Luna
 */
@Singleton
public class EncriptadorContrasenia {
    private String secretKey = "78dfg/(8d7fg8d$%67f7g87d#$%";
    private String salt = "asf7567gsfth123fb%s&sdf/%";
    private byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private Cipher cipher;

    @Inject
    private Logger logger;

    /**
     * Método para encriptar la contraseña.
     * @param contrasenia String de la contraseña a encriptar.
     * @return Array de bytes, null en caso de ocurrir un error.
     */
    public byte[] encrypt(String contrasenia){
        try{
            cipher = iniciaCifrado(Cipher.ENCRYPT_MODE);
            return cipher.doFinal(contrasenia.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            logger.fine("Error al encriptar la contraseña: ".concat(e.getMessage()).concat("Contacte a su administrador."));
        }
        return null;
    }

    /**
     * Método para desencriptar las contraseñas de los usuarios.
     * @param contrasenia Array de bytes para iniciar la desencriptación.
     * @return String, null en caso de ocurrir algún error.
     */
    public String decrypt(byte[] contrasenia){
        try{
            cipher = iniciaCifrado(Cipher.DECRYPT_MODE);
            return new String(cipher.doFinal(contrasenia), StandardCharsets.UTF_8);
        }catch(Exception e){
            logger.fine("Error al desencriptar la contraseña: ".concat(e.getMessage()).concat("Contacte a su adminitrador."));
        }
        return null;
    }

    /**
     * Método para inicar el cifrado.
     * @param cipherMode Inteeger para inicializar Cipher.
     * @return Un objeto del tipo cipher.
     * @throws Exception lanza una Exception del tipo GeneralSecurityException.
     */
    private Cipher iniciaCifrado(Integer cipherMode) throws Exception{
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secretKeyTmp = secretKeyFactory.generateSecret(keySpec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyTmp.getEncoded(), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(cipherMode, secretKeySpec, ivParameterSpec);
        return cipher;
    }

}
