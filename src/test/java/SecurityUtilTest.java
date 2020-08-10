import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class SecurityUtilTest {

  public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    System.out.println("안녕? base64 encode : " + SecurityUtil.encodeBase64("안녕?"));
    System.out.println("안녕? base64 decode : " + SecurityUtil.decodeBase64("7JWI64WVPw=="));
    System.out.println("안녕? sha256 : " + SecurityUtil.sha256("안녕?"));
    System.out.println("안녕? sha512 : " + SecurityUtil.sha512("안녕?"));
  }

}
