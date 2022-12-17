package cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P02_NegativeSpartanTests {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://3.86.105.252:8000";
    }


    @Test
    @DisplayName("Get All Spartans")
    public void getAllSpartans(){
        /*
        Given accept contentType is application/json
        When user sends fET request /api/spartans end point
        Then status code should be 200
         */

        Response response = RestAssured.given().accept(ContentType.JSON).when().get("/api/spartans");

        // how to get status code
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);

    }


    @Test
    @DisplayName("Get ")
    public void test2(){

        /*
        Given Accept type application/xml
        When user send GET request to /api/spartans/10 end point
        Then status cod must be 406
        And response Content Type must be application/xml;charset=UTF-8;
         */

        //Given Accept type application/xml
        //        When user send GET request to /api/spartans/10 end point
        Response response = RestAssured.given().accept(ContentType.XML).when().get("/api/spartans/10");

        //Then status cod must be 406
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(406,actualStatusCode);

        //And response Content Type must be application/xml;charset=UTF-8;
        String actualContentType = response.contentType();

        Assertions.assertEquals("application/xml;charset=UTF-8",actualContentType);
        System.out.println(actualContentType);


    }

}
