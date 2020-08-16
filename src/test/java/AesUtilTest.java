import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AesUtilTest {

  public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
      BadPaddingException, InvalidAlgorithmParameterException {
    String encrypted = AesUtil.encrypt("아무거나내맘대로2342ㄴㅇㄹ2123132", "abcdefghijklmnopqrstuvxyz0123456");
    System.out.println(encrypted);
    System.out.println(AesUtil.decryptAES(encrypted, "abcdefghijklmnopqrstuvxyz0123456"));
  }
}
