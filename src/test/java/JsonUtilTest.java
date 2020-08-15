
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class JsonUtilTest {
  public static void main(String[] args) throws JsonProcessingException {
    Map<String, Object> testMap = new HashMap<>();

    testMap.put("id", "userId");
    testMap.put("password", "userPassword");
    testMap.put("name", "userName");
    testMap.put("birthday", "2020");

    String json = JsonUtil.toJson(testMap);
    System.out.println(json);// {"birthday":"2020","name":"userName","id":"userId"}

    Map<String, Object> reTestMap = JsonUtil.toObject(json, new TypeReference<Map<String, Object>>() {
    });

    System.out.println(reTestMap);// {birthday=2020, name=userName, id=userId}

    String json2 = JsonUtil.toJson(testMap, "birthday");
    System.out.println(json2);// {"name":"userName","id":"userId"}
  }
}
