package getRequest;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PostGetPutDelete {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod Called");
    }
    @Test (description = "Get Method") //(dependsOnMethods = {"testBody"})
    public void testResponseCode() {
        Response response = RestAssured.get("http://localhost:3000/posts");
        String contentType = response.header("Content-Type");
        System.out.println("Content-Type Value : "+contentType);
        int code = response.getStatusCode();
        System.out.println("Status Code is : "+code);
        Assert.assertEquals(code,200);
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println(jsonPathEvaluator.get("name"));
    }

    @Test
    @Parameters("Name")
    public void testBody(String Name) {
        Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        String data = response.getBody().asString();
        System.out.println("Data is : "+ data);
        System.out.println("Response time "+response.getTime());
        System.out.println(Name);
    }

    @Test (description = "Post Method")
    public void testPostMethod() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");

        JSONObject json = new JSONObject();
        //json.put("id","1");
        json.put("name","Karan");
        json.put("title","Lead");

        request.body(json.toJSONString());

        Response response = request.post("http://localhost:3000/posts");
        int code = response.getStatusCode();
        System.out.println(response.body().asString());
        Assert.assertEquals(code,201);
//        String successCode = response.jsonPath().get("SuccessCode");
//        System.out.println(successCode);
//        Assert.assertEquals(successCode,"OPERATION_SUCCESS");
    }

    @Test (description = "Delete Method")
    public void testDeleteMethod() {
        Response response = RestAssured.delete("http://localhost:3000/posts/1");
        int code = response.getStatusCode();
        Assert.assertEquals(code,200);
    }

    @Test(description = "Put Method")
    public void testPutMethod() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");

        JSONObject json = new JSONObject();
        json.put("id","24");  // Can skip this line
        json.put("name","David");
        json.put("title","None");

        request.body(json.toJSONString());

        Response response = request.put("http://localhost:3000/posts/24");
        int code = response.getStatusCode();
        System.out.println(response.body().asString());
        Assert.assertEquals(code,200);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod Called");
    }

}
