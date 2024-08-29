import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
//import io.restassured.RestAssured.*
//import io.restassured.matcher.RestAssuredMatchers.*
//import org.hamcrest.Matchers.*


public class DemoTest {
    String baseURL = "https://reqres.in/";
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequest () {        // test case starts here (as a method)
        Response response = RestAssured.given()   // create instance response for the upcoming request
                .contentType(ContentType.XML)     // specify request content type (JSON or XML)   [Optional]
                .log().all()                      // display request info (headers, URL,...)   [Optional]
                .when()                           // start specifying action type [Optional, no functionality]
                .get(baseURL + "api/users?page=2"); // provide the complete URL with the endpoint

        response.then()                 // start defining response
                .log().all()            // display response info (headers, URL,...)   [Optional]
                .assertThat()           // start specifying assertion chain (things th check) [Optional, no functionality]
                .statusCode(200)     // assert/check that status code is 200
                .body("page", Matchers.equalTo(2));   // assert/check that page field in the response body is equal to 200
    }
}
