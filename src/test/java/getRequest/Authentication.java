package getRequest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Authentication {
    @Test
    public void testAuth() {
        int code = RestAssured.given()
                .auth().preemptive()
                .basic("ToolsQA", "TestPassword")
                .when()
                .get("http://restapi.demoqa.com/authentication/CheckForAuthentication")
                .getStatusCode();
        System.out.println(code);
    }
}
