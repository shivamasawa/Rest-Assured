package getRequest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

public class ReadJsonResponse {
    @Test
    public void readSeperateJsonData() {
        Response response = RestAssured.get("http://localhost:3000/posts");
        ResponseBody body = response.getBody(); // response.body()
        String bodyData = body.asString();
        System.out.println(bodyData);

        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("ID : "+ jsonPathEvaluator.get("id"));
        System.out.println("Name : "+jsonPathEvaluator.getString("name"));
        System.out.println("Title : "+jsonPathEvaluator.get("title"));
    }
}
