
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;


// Static Imports
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DemoTest_QAcart2 {
    String BaseURL = "https://todo.qacart.com/";
    String endPoint = "api/v1/info/courses";

    /*--------------------------------Headers--------------------------------*/
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetCoursesInfo1 () {        // test case starts here (as a method)

        Header nameHeader = new Header ("name", "Selenium");  // create object from header class

        given().baseUri(BaseURL)
                .header( "language", "JAVA")  // .header() method used to send header with the request
                .header( "type", "WEB")       // .header() method used to send header with the request
                .header(nameHeader)                 // sending header object in request via .header() method
                                                    // "language" & "type" key is used to filter the response
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetCoursesInfo2 () {        // test case starts here (as a method)

        Header nameHeader = new Header ("name", "Selenium");  // create object from header class
        Header authorHeader = new Header ("author", "Hatem Hatamleh");  // create object from header class
        Headers infoHeader = new Headers (nameHeader, authorHeader);  // create object from headers class

        given().baseUri(BaseURL)
                .headers( "language", "JAVA", "type", "WEB")  // headers method used to send headers with the request
                .headers(infoHeader)  // sending headers object in request via .headers() method
                // this should be the same output as the previous Test case
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetCoursesInfo3 () {        // test case starts here (as a method)

        HashMap<String, String> infoHeader = new HashMap<> ();  // initializing Hashmap object <Key_datatype: String, Value_datatype: String>
        infoHeader.put("name", "Selenium");
        infoHeader.put("author", "Hatem Hatamleh");

        given().baseUri(BaseURL)
                .headers( "language", "JAVA", "type", "WEB")  // headers method used to send headers with the request
                .headers(infoHeader)  // sending headers object in request via .headers() method
                // this should be the same output as the previous Test case
                .when().get(endPoint)
                .then().log().all()
                .assertThat()
                    .statusCode(200)
                .body("count", equalTo(1));
    }

    /*--------------------------------Query Parameters--------------------------------*/
    String endPoint2 = "api/v1/info/lectures";
    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetLecturesInfo1 () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .when().get(endPoint2)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetLecturesInfo2 () {        // test case starts here (as a method)
        given().baseUri(BaseURL)
                .queryParam("type", "PAID")
                .when().get(endPoint2)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test     // annotation to indicate that the upcoming method is an executable testcase
    public void verifyGetLecturesInfo3 () {        // test case starts here (as a method)
        HashMap<String, String> params = new HashMap<> ();  // initializing Hashmap object <Key_datatype: String, Value_datatype: String>
        params.put("type", "PAID");
        params.put("mode", "ARTICLE");
        given().baseUri(BaseURL)
                .queryParams(params )
                .when().get(endPoint2)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}