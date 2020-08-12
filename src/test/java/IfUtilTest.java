import java.util.Arrays;

public class IfUtilTest {
  public static void main(String[] args) {
    System.out.println(IfUtil.decode("A", "X")); // X
    System.out.println(IfUtil.decode("A", "B", "X"));// null
    System.out.println(IfUtil.decode("A", "B", "X", "Y"));// Y
    System.out.println(IfUtil.decode("A", "B", "X", "A", "Y"));// Y
    System.out.println(IfUtil.decode(null, "B", "X", null, "Y"));// Y
    System.out.println(IfUtil.contains("ABC", "B", "OK", "NOT OK"));// OK
    System.out.println(IfUtil.contains("ABC", "D", "OK", "NOT OK"));// NOT OK
    System.out.println(IfUtil.contains(Arrays.asList("A", "B", "C"), "B", "OK", "NOT OK"));// OK
    System.out.println(IfUtil.contains(Arrays.asList("A", "B", "C"), "D", "OK", "NOT OK"));// NOT OK
    System.out.println(IfUtil.nvl("A", "IS NOT NULL"));// A
    System.out.println(IfUtil.nvl(null, "IS NULL"));// IS NULL
    System.out.println(IfUtil.evl("A", "IS NOT EMPTY"));// A
    System.out.println(IfUtil.evl("", "IS EMPTY"));// IS EMPTY
    System.out.println(IfUtil.evl(null, "IS EMPTY"));// IS EMPTY
  }
}
