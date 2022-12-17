package cydeo.day03;

import cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.Utilities;
import java.util.HashMap;
import java.util.Map;

public class P01_SpartanWithPathParam extends SpartanTestBase {



    @DisplayName("GET Spartan with path param {ID} 24")
    @Test
    public void test1(){

        /*
        Given accept type is Json
        And Id parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Julio" should be in response payload(Body)
         */

        //Given accept type is Json
        //        And Id parameter value is 24
        //        When user sends GET request to /api/spartans/{id}
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("id",24).when().get("/api/spartans/{id}");
        response.prettyPrint();


        //Then response status code should be 200
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);
        System.out.println(actualStatusCode);

        //And response content-type: application/json
        String actualContentType = response.getContentType();
        Assertions.assertEquals("application/json",actualContentType);
        System.out.println(actualContentType);


        //And "Julio" should be in response payload(Body)

        Assertions.assertTrue(response.body().asString().contains("Julio"));

    }

    @DisplayName("GET Spartan /api/spartans/{id} with invalid ID")
    @Test

    public void test2(){

        /*
        Task 2:
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content type: application/json
        And "Not Found" message should be in response payload
         */


        //Given accept type is Json
        //        And Id parameter value is 500
        //        When user sends GET request to /api/spartans/{id}
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParams("id", 500).when().get("/api/spartans/{id}");
        response.prettyPrint();


        //Then response status code should be 404
        Assertions.assertEquals(404,response.getStatusCode());

        //And response content type: application/json
        Assertions.assertEquals("application/json",response.getContentType());

        //And "Not Found" message should be in response payload
        Assertions.assertTrue(response.body().asString().contains("Not Found"));

    }

    @DisplayName("GET spartan query Search /api/spartans/search")
    @Test
    public void Test3(){

        /*
        Given Accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user send Get request to /api/spartans/search
        Then response status code should be 200
        Abd response content-type: application/json
        And "Female" should be in response payload
        And "Jannette" should be in response payload
         */

        Response response = RestAssured.given().accept(ContentType.JSON).and().queryParam("gender", "Female").and().queryParam("nameContains","e")
                .when().get("/api/spartans/search");


        //Then response status code should be 200
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);
        System.out.println(actualStatusCode);

        //And response content-type: application/json
        String actualContentType = response.contentType();
        Assertions.assertEquals("application/json",actualContentType);
        System.out.println(actualContentType);

        //And "Female" should be in response payload
        Assertions.assertTrue(response.body().asString().contains("Female"));

        //And "Jannette" should be in response payload
        Assertions.assertTrue(response.body().asString().contains("Janette"));



    }
    @DisplayName("GET Request /api/spartans/search with Query Params")
    @Test
    public void test4(){

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains","e");

        Response response = RestAssured.given().accept(ContentType.JSON).and().queryParams(queryMap).when().get("/api/spartans/search");
//        response.prettyPrint();

        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);

        String actualContentType = response.contentType();
        Assertions.assertEquals("application/json",actualContentType);

        Assertions.assertTrue(response.body().asString().contains("Female"));

        Assertions.assertTrue(response.body().asString().contains("Janette"));


    }




}
