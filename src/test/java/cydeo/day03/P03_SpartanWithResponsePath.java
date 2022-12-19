package cydeo.day03;

import cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P03_SpartanWithResponsePath extends SpartanTestBase {

    @DisplayName("GET Spartan with")
    @Test
    public void test1(){
    /*
    Given accept type is Json
    And path param id is 10
    When user Sends a GET request to "/api/spartans/{id}"
    Then status code is 200
    And content-Type is "application/json"
    And response payload values match the following:
            id is 10
            name is "Lorenza",
            gender is "Female",
            phone is 3312820936
     */

        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 10).when().get("/api/spartans/{id}");

        response.prettyPrint();

        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200,actualStatusCode);
        System.out.println(actualStatusCode);

        String actualContentType = response.getContentType();
        Assertions.assertEquals("application/json",actualContentType);

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        String address = response.path("address");


        System.out.println("id= " + id);
        System.out.println("name= " + name);
        System.out.println("phone= " + phone);

        System.out.println("address= " + address);

        //Assertions

        Assertions.assertEquals(10,id);
        Assertions.assertEquals("Lorenza",name);
        Assertions.assertEquals("Female",gender);
        Assertions.assertEquals(3312820936l,phone);

    }

    @DisplayName("GET All Spartans")
    @Test
    public void test2(){

        /*
        Get me 1st spartan id

        Get me first spartan name

        Get me last spartan name

        Get me second spartan name from the last

        get me all spartans name
         */

        Response response = RestAssured.given().accept(ContentType.JSON).when().get("/api/spartans");
//        response.prettyPrint();

        //Get me 1st spartan id
        int firstId = response.path("[0].id");
        System.out.println(firstId);


        //Get me first spartan name

        System.out.println("response.path(\"[0].name\")= " + response.path("[0].name"));

        //Get me last spartan name

        System.out.println("response.path(\"[-1].name\")= " + response.path("name[-1]"));


        //Get me second spartan name from the last

        System.out.println( "response.path([-2].name) =" + response.path("name[-2]"));



        //get me all spartans name
        List<String> allNames = response.path("name");


        for (String eachName : allNames){
            System.out.println("Name =" +eachName);
        }

    }



}
