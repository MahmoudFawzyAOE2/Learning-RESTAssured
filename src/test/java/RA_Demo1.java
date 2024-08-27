import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RA_Demo1 {
    String baseURL = "https://reqres.in/";
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequest () {        // test case starts here
        Response response = RestAssured.given()   // create instance response for the upcoming request
                .contentType(ContentType.XML)     // specify request content type (JSON or XML)   [Optional]
                .log().all()                      // display request info (headers, URL,...)   [Optional]
                .when()                           // specify action type
                .get(baseURL + "api/users?page=2");

        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("page", Matchers.equalTo(2));
    }
}
