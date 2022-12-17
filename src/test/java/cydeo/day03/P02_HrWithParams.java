package cydeo.day03;

import cydeo.Utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P02_HrWithParams extends HrTestBase {

    @DisplayName("GET Request /countries with regionID")
    @Test

    public void test1(){

        /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users send a GET request to "/countries"
        Then status code is 200
        And content-type is: application/json
        And payload should contain "United States of America"
         */

        Response response = RestAssured.given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
        response.prettyPrint();


        //Then status code is 200
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200,actualStatusCode);
        System.out.println(actualStatusCode);

        //And content-type is: application/json
        String actualContentType = response.getContentType();
        Assertions.assertEquals("application/json",actualContentType);

        Assertions.assertTrue(response.body().asString().contains("United States of America"));


    }



}
