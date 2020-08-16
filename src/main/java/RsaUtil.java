import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaUtil {
  public static String decryptRsa(String strPrivateKey, String securedValue) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeySpecException {

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(hexToByteArray(strPrivateKey));
    PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

    Cipher cipher = Cipher.getInstance("RSA");
    byte[] encryptedBytes = hexToByteArray(securedValue);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    String decryptedValue = new String(decryptedBytes, "UTF-8");
    return decryptedValue;
  }

  public static String encryptRsa(String strPublicKey, String plainText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException,
      NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(hexToByteArray(strPublicKey));
    PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
    return byteArrayToHex(encryptedBytes);
  }

  public static RsaKey generateKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(1024);
    KeyPair keyPair = generator.genKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();
    return new RsaKey(publicKey, privateKey);
  }

  public static class RsaKey {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RsaKey(PublicKey publicKey, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
      this.publicKey = publicKey;
      this.privateKey = privateKey;
    }

    public PrivateKey getPrivateKey() {
      return privateKey;
    }

    public PublicKey getPublicKey() {
      return publicKey;
    }
  }

  public static byte[] hexToByteArray(String s) {
    byte[] retValue = null;
    if (s != null && s.length() != 0) {
      retValue = new byte[s.length() / 2];
      for (int i = 0; i < retValue.length; i++) {
        retValue[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
      }
    }
    return retValue;
  }

  public static String byteArrayToHex(byte buf[]) {
    StringBuffer strbuf = new StringBuffer();
    for (int i = 0; i < buf.length; i++) {
      strbuf.append(String.format("%02X", buf[i]));
    }

    return strbuf.toString();
  }
}
