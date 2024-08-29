import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;


// Static Imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class DemoTest2 {
    String BaseURL = "https://66ce22ab199b1d628687e033.mockapi.io/aoe2/";
    String endPoint = "Units";

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBasic () {        // test case starts here (as a method)
        // here we use the static imports to create test cases without using Response class
        /*
        given()     // include info in request (Parameters, Headers, Auth, Body,...)
        .when()     // include Action in request (GET, POST, DELETE, Put,...)
        .then();    // include response data to be verified (Response headers & body, Status code, Cookies,...)
        */
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then().log().all();
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestStatusCode () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then()
                .log().status()                        // use this to display only the status code
                .statusCode(200);

    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestContentTypeJSON () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then()
                .log().headers()                        // use this to display only the headers
                .contentType(ContentType.JSON);
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestParameters () {        // test case starts here (as a method)
        given().baseUri(BaseURL).log().parameters()  // use this to display request parameters
                .when().get(endPoint)
                .then()
                .log().ifError();  // will print logs only if test case failed

    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyEqualTo () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then().assertThat().body("[0].id", equalTo("1"));
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyHasItem () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then()
                .log().ifValidationFails() // will display logs only if test case fails (woks only with assertions)
                .assertThat().body("id", hasItem("1"));
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyHasItems () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then().assertThat().body("id", hasItems("1", "2", "3"));
    }
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyNotEmpty () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then().assertThat().body("id", not(empty()));
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyContain () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then()
                .assertThat().body("id", contains("1", "2", "3","4","5","6","7")) // verify all elements &  verify order
                .assertThat().body("id", containsInAnyOrder("1", "4", "7","2","6","3","5")); // verify all elements & not verify order
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyHasSize () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then()
                .assertThat().body("id", hasSize(7)) // verify array size
                .assertThat().body("id.size()", equalTo(7)); // verify array size (alternative method)
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyEveryItem () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then()
                .assertThat().body("createdAt", everyItem(startsWith("2024"))) // verify all elements satisfy a condition
                .assertThat().body("createdAt", everyItem(endsWith("Z"))); // verify all elements satisfy a condition
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyHasKeyValueEntry () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint)
                .then()
                .assertThat().body("[0]", hasKey("name")) // verify JSON object has a key called "name"
                .assertThat().body("[0]", hasValue("Saudi Arabia")) // verify JSON object has a value called "Saudi Arabia"
                .assertThat().body("[0]", hasEntry("id", "1")); // verify JSON object has a key-value pair called "id": "1"
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetRequestBodyGetVar () {        // test case starts here (as a method)
        Response res = given().baseUri(BaseURL)
                .when().get(endPoint)
                .then().extract().response();

        System.out.println(res.asString());   // this will print the entire response in one line

        String name = res.path("[0].name");  // extracting value from response
        System.out.println(name);   // this will print the value associated with key "name" in the first object

        String name2 = JsonPath.read(res.asString(),"[0].name");  // extracting value via json path
        System.out.println(name2);   // this will print the value associated with key "name" in the first object
    }
}
