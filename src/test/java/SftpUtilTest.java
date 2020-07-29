import java.io.File;

public class SftpUtilTest {
  public static void main(String[] args) {
    String remoteDir = "";
    File uploadFile = new File("C:\\Users\\goni\\Pictures\\test.png");
    String url = "";
    String user = "";
    String password = "";
    boolean result = SftpUtil.upload(url, user, password, remoteDir, uploadFile);
    if (result) {
      System.out.println("업로성공");
    } else {
      System.out.println("업로실패");
    }
  }
}
