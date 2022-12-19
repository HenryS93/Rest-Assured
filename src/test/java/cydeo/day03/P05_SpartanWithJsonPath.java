package cydeo.day03;

import cydeo.Utilities.HrTestBase;
import cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class P05_SpartanWithJsonPath extends SpartanTestBase {


    @DisplayName("My Work: GET Spartan with Json")
    @Test
    public void test(){

        /*
        Given accept type is json
        And path param id is 10
        When user sends a GET Request to /api/spartans/{id}
        Then status code is 200
        And Content type is "application/json"
        And response payload values match the following
            ID is 10
            name is "Lorenza"
            gender is "Female"
            phone is 3312820936
         */

        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 10).when().get("/api/spartans/{id}");
        response.prettyPrint();

        //Then status code is 200
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);

        //And Content type is "application/json"
        String actualContentType = response.getContentType();
        Assertions.assertEquals("application/json",actualContentType);

        //And response payload values match the following

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        Assertions.assertEquals(10,id);
        Assertions.assertEquals("Lorenza",name);
        Assertions.assertEquals("Female",gender);
        Assertions.assertEquals(3312820936l,phone);

    }

    // FROM: Class
    @DisplayName("GET Spartan with Json")
    @Test
    public void test1() {

        /*
        Given accept type is json
        And path param id is 10
        When user sends a GET Request to /api/spartans/{id}
        Then status code is 200
        And Content type is "application/json"
        And response payload values match the following
            ID is 10
            name is "Lorenza"
            gender is "Female"
            phone is 3312820936
         */

        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 10).when().get("/api/spartans/{id}");
        response.prettyPrint();

        //Then status code is 200
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200, actualStatusCode);

        //We saved as a JSONPATH OBject
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");


        Assertions.assertEquals(10,id);
        Assertions.assertEquals("Lorenza",name);
        Assertions.assertEquals("Female",gender);
        Assertions.assertEquals(3312820936l,phone);


    }

    }
