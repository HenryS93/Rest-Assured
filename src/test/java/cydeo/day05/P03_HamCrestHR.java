package cydeo.day05;

import cydeo.Utilities.HrTestBase;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;

public class P03_HamCrestHR extends HrTestBase {

    @DisplayName("Get")
    @Test
    public void test1(){

        /*
        Given accept type is Json
        And Parameters: q = {"job_id":"IT_PROG"}
        When users sends a GET Request to "/employees"
        Then status code is 200
        And Content-Type is application/Json

        Verify:
                -each employee has manager
                -each employees working as IT-PROG
                -each of them getting salary greater than 3000
                -first_name are .... (Find proper method to check list against list)
                -emails without checking order (Provide emails in different order, just make sure it has same emails)

         */

        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given().accept(ContentType.JSON).queryParam("q","{\"job_id\":\"IT_PROG\"}").when().get("/employees").prettyPeek().then().statusCode(200).contentType("application/json")
                .body("items.manager_id",everyItem(notNullValue()))
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.salary",everyItem(greaterThan(3000)))
                .body("items.first_name", equalTo(names))
                .body("items.email",containsInAnyOrder("DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"));


    }

    @DisplayName("GET Request from regions")
    @Test
    public void test2(){

        /*
        Given accept Type is application/json
        When user sends get request to /regions
        Then
        response code status must be 200
        verify Date has values
        first region name is Europe
        first region id is 1
        four regions we have
        region names are not null
        Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
        region ids needs to be 1,2,3,4

        Print all the regions names
         */


        JsonPath jsonPath = given().accept(ContentType.JSON).when().get("/regions").prettyPeek()
                .then().statusCode(200)
                .and().contentType("application/json")
                .headers("Date", notNullValue())
                .body("items[0].region_name", is(equalTo("Europe")))
                .body("items[0].region_id", is(equalTo(1)))
                .body("items.region", hasSize(4))
                .body("items.region_name", notNullValue())
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4)).extract().response().jsonPath();


        //print all region names
        List<String> regionNameList = jsonPath.getList("items.region_name");
        System.out.println("regionNameList = " + regionNameList);

        //Print all region names
        //Assume that we are gonna verify region names API response
        List<String> actualRegionNameList = jsonPath.getList("items.region_name");
        System.out.println("actualRegionNameList = " + actualRegionNameList);


        //Get all region names from database










    }

}
