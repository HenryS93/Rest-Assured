package cydeo.practice;

import cydeo.Utilities.CydeoTrainingTestBase;
import cydeo.Utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Practice extends HrTestBase {

    @Test
    public void test1(){

        /*
          TASK
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK
         */

        Response response = RestAssured.given().accept(ContentType.JSON).when().get("/locations");
        response.prettyPrint();

        //response status code must be 200
        Assertions.assertEquals(HttpStatus.SC_OK,response.getStatusCode());

        //content type equals to application/json
        Assertions.assertEquals("application/json",response.getContentType());


        //get the second city with JsonPath
        JsonPath jsonPath = response.jsonPath();
        String SecondCity = jsonPath.getString("items[1].city");


        //get the last city with JsonPath
        String lastCity = jsonPath.getString("items[-1].city");


        //get all country ids
        List<String> allIds = jsonPath.getList("items.country_id");
            System.out.println(allIds);


         //get all city where their country id is UK
        //"items.findAll{it.job_id=='IT_PROG'}.email"
        List<String> allCityList = jsonPath.getList("items.findAll{it.country_id == 'UK'}.city");
        System.out.println(allCityList);

    }

}
