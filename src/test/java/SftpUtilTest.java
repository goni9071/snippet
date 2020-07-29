import java.io.File;

public class SftpUtilTest {
  public static void main(String[] args) {
    String remoteDir = "/reditus2/www/download";
    File uploadFile = new File("C:\\Users\\정현우\\Pictures\\test.png");
    String url = "reditus2.cafe24.com";
    String user = "reditus2";
    String password = "fpelxntm2020!";
    boolean result = SftpUtil.upload(url, user, password, remoteDir, uploadFile);
    if (result) {
      System.out.println("업로성공");
    } else {
      System.out.println("업로실패");
    }
  }
}
