package entidades;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Maneja el uso de encriptaciones, cadenas y sal
 * Clase documentada
 * @author Jorge Zamora y Victoria Vega
 */
public class Cifrado {
    
    public static String generarSal() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 128 bits
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String encriptarCadena(String texto, String sal) throws Exception {
        byte[] iv = new byte[16]; // Inicialización de vector de inicialización (IV)
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        SecretKeySpec clave = new SecretKeySpec(sal.getBytes(), "AES");

        Cipher cifrador = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, clave, ivParameterSpec);

        byte[] textoCifrado = cifrador.doFinal(texto.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(iv) + ":" + Base64.getEncoder().encodeToString(textoCifrado);
    }

    public static String descifrarCadena(String textoCifrado, String sal) throws Exception {
        String[] partes = textoCifrado.split(":");
        byte[] iv = Base64.getDecoder().decode(partes[0]);
        byte[] textoCifradoBytes = Base64.getDecoder().decode(partes[1]);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec clave = new SecretKeySpec(sal.getBytes(), "AES");

        Cipher cifrador = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifrador.init(Cipher.DECRYPT_MODE, clave, ivParameterSpec);

        byte[] textoBytes = cifrador.doFinal(textoCifradoBytes);

        return new String(textoBytes, "UTF-8");
    }
    
}