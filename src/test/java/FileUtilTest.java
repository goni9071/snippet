import java.io.IOException;

public class FileUtilTest {
  public static void main(String[] args) throws IOException {
    String targetFile = "D:/test/test.txt";
    FileUtil.write("안녕하세요", targetFile);
    System.out.println(FileUtil.readFileForText(targetFile)); // 안녕하세요
    FileUtil.write("아니", targetFile);
    System.out.println(FileUtil.readFileForText(targetFile)); // 아니
  }
}
