package cydeo.day03;

import cydeo.Utilities.HrTestBase;
import cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class P04_HrWithResponsePath extends HrTestBase {


    @DisplayName("GET request to countries with using  Response Path")
    @Test
    public void test1(){

        /*
          Print limit
          Print hasMore
          Print second country_id
          Print 4th element country name
          Print 4th Elements Href
         */
        Response response = RestAssured.given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        // Print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        // Print hasMore
        System.out.println("response.hasMore = " + response.path("hasMore"));


        // Print second country_id
        System.out.println("response.path(\"items[1].country_id\") = " + response.path("items[1].country_id"));

        //Print 4th element country name
        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));


        //           Print 4th Elements Href
        System.out.println("response.path(\"items[3].links[0].href\") = " + response.path("items[3].links[0].href"));


        List<String> allCountries = response.path("items.country_name");

        for (String eachCountry : allCountries){
            System.out.println("eachCountry = " + eachCountry);
        }

        //Verify all region_id is 2

        List<Integer> regionIdIs2 = response.path("items.region_id");

        for (Integer eachRegion : regionIdIs2){

            Assertions.assertEquals(2,eachRegion);
            System.out.println(eachRegion);
        }

        //Another solution
        Assertions.assertTrue(regionIdIs2.stream().allMatch(each -> each == 2));

    }


    @DisplayName("GET Request from Employees Job_Id = IT_PROG")
    @Test
    public void test2(){

    /*
    Send a GET Request to Employees endpoint to see only job_id = IT_PROG
    Query Param
        q = {"job_id":"IT_PROG"}

    Then assert all job job_ids are IT_PROG
     */

        Response response = RestAssured.given().accept(ContentType.JSON).queryParam("q", "{\"job_id\":\"IT_PROG\"}").when().get("/employees");

        response.prettyPrint();

       List<String> allITJob_ID = response.path("items.job_id");

        System.out.println(allITJob_ID.stream().allMatch(each-> each.matches ("IT_PROG")));



    }







}
