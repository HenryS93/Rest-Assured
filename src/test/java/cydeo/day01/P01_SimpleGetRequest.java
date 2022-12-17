package cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SimpleGetRequest {

    String url = "http://3.86.105.252:8000/api/spartans";

    /**
     * When users  end request to /API/Spartans endpoint
     * Then user should be able to see status code is 200
     * And Print out Response body into screen
     */

    @Test
    public void simpleGetRequest(){

        //Send request to url and get response as interface

        Response response = RestAssured.get(url);


        //Both same there's no difference

        System.out.println("Response.getStatusCode() = "+response.getStatusCode());
        System.out.println("Response.statusCode()= " + response.statusCode());
        // gives all status line
        System.out.println("response.statusLine()= " +response.statusLine());


        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200,actualStatusCode);

        //how to print into screen

        response.prettyPrint();


    }


}
