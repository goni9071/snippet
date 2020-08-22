import java.math.BigDecimal;

/**
 * JAVA 소수점 곱하기 테스트
 * 
 */
public class DecimalMultiplyTest {
  public static void main(String[] args) {
    System.out.println("----TEST1----");
    test1();
    System.out.println("----TEST2----");
    test2();
  }

  /**
   * <pre>
   * 결과 1 : 61600.0
   * 결과 2 : 61600.00000000001
   * 결과 3 : 61600.00133514404296875000000
   * 결과 4 : 61600.000000000004973799150320701301097869873046875000000
   * 결과 5 : 61600.0
   * </pre>
   */
  private static void test1() {
    int a = 14000;
    int b = 4;
    float c = 1.1f;
    double d = 1.1d;

    System.out.println("결과 1 : " + a * b * c); // 61600.0
    System.out.println("결과 2 : " + a * b * d); // 61600.00000000001

    {
      BigDecimal bigDecimal1 = new BigDecimal(a);
      BigDecimal bigDecimal2 = new BigDecimal(b);
      BigDecimal bigDecimal3 = new BigDecimal(c);
      System.out.println("결과 3 : " + bigDecimal1.multiply(bigDecimal2).multiply(bigDecimal3)); // 61600.00133514404296875000000
    }
    {
      BigDecimal bigDecimal1 = new BigDecimal(a);
      BigDecimal bigDecimal2 = new BigDecimal(b);
      BigDecimal bigDecimal3 = new BigDecimal(d);
      System.out.println("결과 4 : " + bigDecimal1.multiply(bigDecimal2).multiply(bigDecimal3)); // 61600.000000000004973799150320701301097869873046875000000
    }

    {
      BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(a));
      BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(b));
      BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(c));
      System.out.println("결과 5 : " + bigDecimal1.multiply(bigDecimal2).multiply(bigDecimal3)); // 61600.0
    }
  }

  /**
   * <pre>
   * 결과 1 : 2.1600018E7
   * 결과 2 : 2.1600018E7
   * 결과 3 : 21600018.46816778182983398437500
   * 결과 4 : 21600018.000000001744060895703114510979503393173217773437500
   * 결과 5 : 21600018.0
   * </pre>
   */
  private static void test2() {
    Integer a = 327273;
    Long b = 60L;
    float c = 1.1f;
    double d = 1.1d;

    System.out.println("결과 1 : " + a * b * c); // 61600.0
    System.out.println("결과 2 : " + a * b * d); // 61600.00000000001

    {
      BigDecimal bigDecimal1 = new BigDecimal(a);
      BigDecimal bigDecimal2 = new BigDecimal(b);
      BigDecimal bigDecimal3 = new BigDecimal(c);
      System.out.println("결과 3 : " + bigDecimal1.multiply(bigDecimal2).multiply(bigDecimal3)); // 61600.00133514404296875000000
    }
    {
      BigDecimal bigDecimal1 = new BigDecimal(a);
      BigDecimal bigDecimal2 = new BigDecimal(b);
      BigDecimal bigDecimal3 = new BigDecimal(d);
      System.out.println("결과 4 : " + bigDecimal1.multiply(bigDecimal2).multiply(bigDecimal3)); // 61600.000000000004973799150320701301097869873046875000000
    }

    {
      BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(a));
      BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(b));
      BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(c));
      System.out.println("결과 5 : " + bigDecimal1.multiply(bigDecimal2).multiply(bigDecimal3)); // 61600.0
    }
  }
}
