package cydeo.day06;

import static io.restassured.RestAssured.*;

import cydeo.Utilities.HrTestBase;
import cydeo.Utilities.SpartanTestBase;
import cydeo.pojo.Search;
import cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class P02_SpartanDeserilizationPOJO extends SpartanTestBase {


    @DisplayName("GET Single Spartan for Deserilization to POJO")
    @Test
    public void test1(){


        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200).extract().response();




            /*
            output:


            {

            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106

            }

             */


        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);

        System.out.println("=================Printed Individually========================");
        System.out.println("Using Response");
        //Response
        System.out.println(spartan.getId());
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getPhone());


        //JSONPATH
        System.out.println("========================Print with JsonPath=================");
        JsonPath jsonPath = response.jsonPath();
        Spartan sp = jsonPath.getObject("", Spartan.class);

        System.out.println("sp = " + sp);

        System.out.println("sp.getId()= " + sp.getId());
        System.out.println("sp.getName()= " + sp.getName());
        System.out.println("sp.getGender()= " + sp.getGender());
        System.out.println("sp.getPhone()= " + sp.getPhone());



    }

    @DisplayName("GET Spartans from Search endpoint for Deserialization to POJO")
    @Test
    public void test2(){


        Response response = given().accept(ContentType.JSON).when().get("/api/spartans/search")
                .then().statusCode(200).extract().response();


        //RESPONSE
        System.out.println("---------RESPONSE GET ONE SPARTAN--------------------");
        //response.as()  --> Since we CANNOT put path in here to GET specific part of the response
        //We are not gonna do it

        //JSONPATH
        System.out.println("---------------JSONPATH GET ONE SPARTAN--------------------");
        JsonPath jsonPath = response.jsonPath();
        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("spartan = " + spartan);


    }

    @DisplayName("GET SPARTANS FROM SEARCH ENDPOINT FOR DESERIALIZATION TO SEARCH CLASS")
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then()
                .statusCode(200).extract().response();

        System.out.println("-------------------JSONPATH - GET FIRST SPARTAN------------------");


        JsonPath jsonPath = response.jsonPath();

        Search search = jsonPath.getObject("", Search.class);
        System.out.println("search.getContent().get(0)= " + search.getContent().get(0));
        System.out.println("search.getContent().get(0)= " + search.getContent().get(0).getName());



    }




}
