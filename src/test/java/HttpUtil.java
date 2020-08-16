
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.apache.http.client.protocol.HttpClientContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * HttpUtil maven dependency
 * 
 * <pre>
 * <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
 *  <dependency>
 *    <groupId>org.apache.httpcomponents</groupId>
 *    <artifactId>httpclient</artifactId>
 *    <version>4.5.12</version>
 *  </dependency>
 *  <!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
 *  <dependency>
 *    <groupId>org.springframework</groupId>
 *    <artifactId>spring-web</artifactId>
 *    <version>5.2.8.RELEASE</version>
 *  </dependency>
 * </pre>
 */
public class HttpUtil {

  public static RestTemplate getRestTemplate(final HttpClientContext context) {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(10000); // milliseconds
    factory.setConnectTimeout(5000);// milliseconds
    RestTemplate restOperations = new RestTemplate(factory);
    return restOperations;
  }

  public static HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept-Encoding", "gzip, deflate, sdch");
    headers.set("Connection", "keep-alive");
    headers.set("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
    headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36");
    return headers;
  }

  public static byte[] download(String url) {
    HttpClientContext context = HttpClientContext.create();
    RestTemplate restOperations = HttpUtil.getRestTemplate(context);
    HttpHeaders headers = HttpUtil.getHeaders();
    ResponseEntity<byte[]> exchange = restOperations.exchange(url, HttpMethod.GET, new HttpEntity<MultiValueMap<String, Object>>(headers), byte[].class);
    return exchange.getBody();
  }

  /**
   * POST 요청
   * 
   * @param context
   * @param twsid
   * @param answer
   * @return
   */
  public static String post(String url, MultiValueMap<String, Object> parts, String charset) {
    HttpClientContext context = HttpClientContext.create();
    RestTemplate restOperations = HttpUtil.getRestTemplate(context);
    restOperations.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName(charset)));
    HttpHeaders headers = HttpUtil.getHeaders();
    ResponseEntity<String> exchange = restOperations.exchange(url, HttpMethod.POST, new HttpEntity<MultiValueMap<String, Object>>(parts, headers), String.class);
    String body = exchange.getBody();
    HttpStatus statusCode = exchange.getStatusCode();
    if (statusCode != HttpStatus.OK) {
      body = null;
    }
    return body;
  }

  public static String post(String url, MultiValueMap<String, Object> parts) {
    return post(url, parts, "UTF-8");
  }

  public static String get(String url) {
    HttpClientContext context = HttpClientContext.create();
    RestTemplate restOperations = HttpUtil.getRestTemplate(context);
    return get(url, restOperations);
  }

  public static String get(String url, RestTemplate restOperations) {
    HttpHeaders headers = HttpUtil.getHeaders();
    ResponseEntity<String> exchange = restOperations.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);
    return exchange.getBody();
  }

  /**
   * URL을 호출한 결과값을 String으로 반환한다.
   * 
   * @param strUrl  url of string
   * @param body
   * @param charSet
   * @return
   * @throws Exception
   */
  public static String getURLResponseToStringByPost(String strUrl, String body, String charSet) throws Exception {

    String result = null;

    StringBuffer buff = new StringBuffer();
    DataOutputStream out = null;
    BufferedReader in = null;
    InputStreamReader ins = null;
    try {
      URL url = new URL(strUrl);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setConnectTimeout(30000);
      out = new DataOutputStream(conn.getOutputStream());
      out.write(body.getBytes(charSet));
      out.flush();
      out.close();

      ins = new InputStreamReader(conn.getInputStream(), charSet);
      in = new BufferedReader(ins);

      String read;
      while ((read = in.readLine()) != null) {
        buff.append(read + "\n");
      }

      result = buff.toString();
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (Exception ignored) {
          throw new Exception(ignored);
        }
      }

      if (ins != null) {
        try {
          ins.close();
        } catch (Exception ignored) {
          throw new Exception(ignored);
        }
      }
    }

    return result;
  }

  public static String postStringBody(String url, Object request) {
    return postStringBody(url, request, "euc-kr");
  }

  public static String postStringBody(String url, Object request, String charsetName) {
    HttpClientContext context = HttpClientContext.create();
    RestTemplate restOperations = HttpUtil.getRestTemplate(context);
    restOperations.getMessageConverters().add(1, new StringHttpMessageConverter(Charset.forName(charsetName)));
    String result = "";
    ResponseEntity<String> resEntity = restOperations.postForEntity(url, request, String.class);
    result = resEntity.getBody();
    return result;
  }

  public static String post(String url, HttpHeaders headers, String requestBody, String charset) {
    HttpClientContext context = HttpClientContext.create();
    RestTemplate restOperations = HttpUtil.getRestTemplate(context);
    restOperations.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName(charset)));
    ResponseEntity<String> exchange = restOperations.exchange(url, HttpMethod.POST, new HttpEntity<String>(requestBody, headers), String.class);
    String body = exchange.getBody();
    HttpStatus statusCode = exchange.getStatusCode();
    if (statusCode != HttpStatus.OK) {
      body = null;
    }
    return body;
  }
}
