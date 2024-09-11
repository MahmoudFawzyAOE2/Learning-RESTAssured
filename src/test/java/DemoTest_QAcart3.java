import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.LoginPojo;
import org.testng.annotations.Test;


// Static Imports
import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DemoTest_QAcart3 {

    String access_token ;
    String BaseURL = "https://todo.qacart.com/";
    String endPoint = "api/v1/students/login";

    // o7+%86Vv

    /*--------------------------------Headers--------------------------------*/
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyPostLogin1 () {        // test case starts here (as a method)

        // here we create the body to be sent in the request
        String body = "{\n" +
                "    \"email\" : \"mft1998@gmail.com\",\n" +
                "    \"password\" : \"o7+%86Vv\"\n" +
                "}";

        given().baseUri(BaseURL)
                /* Content-Type header must be added to the request, so the server reads the body as a json file,
                   you can use only one of the 2 methods below */
                .header("Content-Type", "application/json")   // method 1
                .contentType(ContentType.JSON)                      // method 2
                .body(body)               // we add argument body (string from object) to the method .body()
                .when().post(endPoint)      // here we post the body in the request to the server
                .then().log().all()
                .assertThat().statusCode(200)
                .body("access_token", not(empty()));
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyPostLogin2 () {        // test case starts here (as a method)

        // here we create the body to be sent in the request
        File body = new File("src/test/resources/login.json"); // path starts from folder after

        given().baseUri(BaseURL)
                .header("Content-Type", "application/json")   // method 1
                .body(body)               // we add argument body (string from file) to the method .body()
                .when().post(endPoint)      // here we post the body in the request to the server
                .then().log().all()
                .assertThat().statusCode(200)
                .body("access_token", not(empty()));
    }

    @Test(priority = 1)             // annotation to indicate that the upcoming method is an executable testcase
    public void verifyPostLogin3 () {        // test case starts here (as a method)

        // here we create the body to be sent in the request
        HashMap<String, String> body = new HashMap<> ();  // initializing Hashmap object <Key_datatype: String, Value_datatype: String>
        body.put("email", "mft1998@gmail.com");
        body.put("password", "o7+%86Vv");
        /* Note: in order to convert hashmap to JSON (serialization), add Jackson Databind repository to the dependencies
                 https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind   */

        given().baseUri(BaseURL)
                .header("Content-Type", "application/json")   // method 1
                .body(body)               // we add argument body (string from HashMap) to the method .body()
                .when().post(endPoint)      // here we post the body in the request to the server
                .then().log().all()
                .assertThat().statusCode(200)
                .body("access_token", not(empty()));
    }

    @Test(priority = 2)      // annotation to indicate that the upcoming method is an executable testcase
    public void verifyPostLogin4 () {        // test case starts here (as a method)

        // here we create the body to be sent in the request
        LoginPojo body = new LoginPojo();
        body.setEmail("mft1998@gmail.com");
        body.setPassword("o7+%86Vv");

        Response res = given().baseUri(BaseURL)
                .header("Content-Type", "application/json")   // method 1
                .body(body)               // we add argument body (string from PojoClass) to the method .body()
                .when().post(endPoint)      // here we post the body in the request to the server
                .then().log().all()
                .assertThat().statusCode(200)
                .body("access_token", not(empty())).extract().response();

        access_token = res.path("access_token");  // extracting value from response
        System.out.println(access_token);   // this will print the value associated with key "name" in the first object
    }

    @Test(priority = 3)      // annotation to indicate that the upcoming method is an executable testcase
    public void verifyPostLogin5 () {        // test case starts here (as a method)

        // here we create the body to be sent in the request
        LoginPojo body = new LoginPojo("mft1998@gmail.com", "o7+%86Vv");
        
        given().baseUri(BaseURL)
                .header("Content-Type", "application/json")   // method 1
                .body(body)               // we add argument body (string from PojoClass) to the method .body()
                .when().post(endPoint)      // here we post the body in the request to the server
                .then().log().all()
                .assertThat().statusCode(200)
                .body("access_token", not(empty()));
    }

    /*--------------------------------Headers--------------------------------*/
    String endPoint2 = "api/v1/courses/6319b5655ce1f40db1b73738";

    @Test(priority = 4)      // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetCourseAuthorization1 () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint2)
                .then().log().all()
                .assertThat().statusCode(401);       // no access without authorization
    }

    @Test(priority = 5)      // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetCourseAuthorization2 () {        // test case starts here (as a method)

        String Auth_token = "Bearer " + access_token;
        System.out.println(Auth_token);

        given().baseUri(BaseURL)
                .header("Authorization", Auth_token)
                .when().get(endPoint2)
                .then().log().all()
                .assertThat().statusCode(401);       // no access without authorization
    }
}