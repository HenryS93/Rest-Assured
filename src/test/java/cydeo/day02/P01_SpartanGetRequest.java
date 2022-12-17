package cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequest {




    String url = "http://3.86.105.252:8000";

    /**
     *
     * Given accept content is application/json
     * When user sends GET Request/ Api/spartans endpoint
     * Then status code should be 200
     * And Content Type should be application/json
     */

    @Test
    public void getAllSpartans(){

        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans");

        // print response
        //ANS-
//        response.prettyPrint();

        // how to get status code?
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200,actualStatusCode);

        //how can we get ContentType?
        String actualContentType = response.getContentType();
        Assertions.assertEquals("application/json",actualContentType);


        //How to get Header info
        String connection = response.header("Connection");
        System.out.println("connection"+connection);


        //get connection type with header

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));



        //Can we get connection() same as contentType() instead of Using Header?
        //A-> Rest Assured created a couple of methods for common use
        // statusCode() contentType() methods are specifically created by them. so there is connection method


        //get the header
        System.out.println("response.header(\"Date\") = " + response.header("Date"));


        //How can we verify date exists?

        boolean hasHeaderWithName = response.headers().hasHeaderWithName("Date");

        Assertions.assertTrue(hasHeaderWithName);



    }
    /*
    Given accept content Type is Application/json
    when the user sends GET request /api/spartans/3 and point
    Then status code should be 200
    And Content Type should be application/json
    And response body needs to contain Fidole
     */


    @DisplayName("Get Single Spartan")
    @Test
    public void getSpartan(){


        //Given accept content Type is Application/json
        //when the user sends GET request /api/spartans/3 and point
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans/3");

        //Then status code should be 200
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);

        //And Content Type should be application/json
        String actualHeader = response.header("Content-Type");
        Assertions.assertEquals("application/json",actualHeader);

        //And response body needs to contain Fidole

        boolean fidole = response.body().asString().contains("Fidole");
        System.out.println(fidole);
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
        /*
        This is NOT a good way to make Assertion. In this we are just converting response toString and with the help of string contains
        we are just looking into Response. In Real we need to verify is Fiddole. That's we need access name key to get value
         */

        //What id we don't have related header or if we have typo it will return NULL
        System.out.println("response.header(\"KeepAlive\") = " + response.header("KeepAlive")); // --> returns NULL typo
        System.out.println("response.header(\"keep-Alive\") = " + response.header("keep-Alive")); //> actual correct


    }

    @DisplayName("Get Hello Sparta")
    @Test
    public void helloSpartan(){

        /*
        Task3:
        Given No headers provided
        When users send GET request to /api/hello
        Then response status code should be 200
        And Content Type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And content-length should be 17
        and body should be "Hello from sparta"
        */

        Response response = RestAssured.given().when().get(url + "/api/hello");
        response.prettyPrint();

       // Then response status code should be 200
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200,actualStatusCode);
        System.out.println(actualStatusCode);

        //And Content Type header should be "text/plain;charset=UFT-8"
        String actualContentType = response.header("Content-Type");
        Assertions.assertEquals("text/plain;charset=UTF-8",actualContentType);
        System.out.println(actualContentType);

        //And header should contain Date
        boolean actuaalDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(actuaalDate);
        System.out.println(actuaalDate);

        //And content-length should be 17
//        String actualContentLength = response.header("Content-Length");
//        Assertions.assertEquals("17",actualContentLength);

        String actualContentLength = response.headers().getValue("Content-Length");
        Assertions.assertEquals("17",actualContentLength);
        System.out.println(actualContentLength);


        //and body should be "Hello from sparta"

        Assertions.assertTrue(response.body().asString().contains("Hello from Sparta"));


    }
}
