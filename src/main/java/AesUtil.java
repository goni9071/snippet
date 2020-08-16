import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
  public static byte[] ivBytes = { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };

  public static String encrypt(String text, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    if (text == null || text.length() == 0) {
      return text;
    }
    String encrypted = null;
    byte[] source = text.getBytes("UTF-8");

    SecretKeySpec skeySpec =

        new SecretKeySpec(key.getBytes("UTF-8"), "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
    AlgorithmParameterSpec IVspec = new IvParameterSpec(ivBytes);
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IVspec);
    int mod = source.length % 16;
    if (mod != 0) {
      text = String.format(text + "%" + (16 - mod) + "s", " ");
    }
    encrypted = byteArrayToHex(cipher.doFinal(text.getBytes("UTF-8")));
    return encrypted;
  }

  public static String decryptAES(String s, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    if (s == null || s.length() == 0) {
      return s;
    }
    String decrypted = null;

    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
    AlgorithmParameterSpec IVspec = new IvParameterSpec(ivBytes);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, IVspec);
    decrypted = new String(cipher.doFinal(hexToByteArray(s)), "UTF-8");
    return decrypted.trim();
  }

  private static byte[] hexToByteArray(String s) {
    byte[] retValue = null;
    if (s != null && s.length() != 0) {
      retValue = new byte[s.length() / 2];
      for (int i = 0; i < retValue.length; i++) {
        retValue[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
      }
    }
    return retValue;
  }

  private static String byteArrayToHex(byte buf[]) {
    StringBuffer strbuf = new StringBuffer();
    for (int i = 0; i < buf.length; i++) {
      strbuf.append(String.format("%02x", buf[i]));
    }
    return strbuf.toString();
  }
}