import java.util.Date;

public class FormatUtilTest {
  public static void main(String[] args) {
    System.out.println(FormatUtil.concat("A", "B"));// AB
    System.out.println(FormatUtil.getBizNo("1234567890"));// 123-45-67890
    System.out.println(FormatUtil.getDayName(new Date()));// 토
    System.out.println(FormatUtil.getEmailId("abc@test.com"));// abc
    System.out.println(FormatUtil.getEmailServerName("abc@test.com"));// test.com
    System.out.println(FormatUtil.getFileSize(123123));// 120.2KB
    System.out.println(FormatUtil.getMobile("01012341234"));// 010-1234-1234
    System.out.println(FormatUtil.getNumber(123123));// 123,123
    System.out.println(FormatUtil.getSecurity("홍길동"));// 홍*동
    System.out.println(FormatUtil.getSecurityBirthday("20200815"));// 20**0815
    System.out.println(FormatUtil.getSecurityMobile("010-1234-1234"));// 010-****-1234
    System.out.println(FormatUtil.getSecurityEmail("abc@test.com"));// a*c@t**t.com
    System.out.println(FormatUtil.getTime("235959"));// 23:59:59
    System.out.println(FormatUtil.isTel("01012341234"));// true
    System.out.println(FormatUtil.lpad("1", "0", 2));// 01
    System.out.println(FormatUtil.getDate(new Date()));// 2020-08-15
    System.out.println(FormatUtil.getDate("20200815"));// 2020-08-15
  }
}
