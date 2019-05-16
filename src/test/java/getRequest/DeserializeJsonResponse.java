package getRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DeserializeJsonResponse {
    @Test
    public void getSuccessCode() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");

        JSONObject json = new JSONObject();
        json.put("id","12");
        json.put("name","Mukesh");
        json.put("profile","Automation QA");

        request.body(json.toJSONString());

        Response response = request.post("http://localhost:3000/posts");

        ResponseBody body = response.getBody();

        RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
        Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
        Assert.assertEquals("Operation completed successfully", responseBody.Message);
    }
}
