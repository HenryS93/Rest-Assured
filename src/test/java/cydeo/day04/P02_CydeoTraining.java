package cydeo.day04;

import cydeo.Utilities.CydeoTrainingTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P02_CydeoTraining extends CydeoTrainingTestBase {



    @DisplayName("GET /student/{id} 2")
    @Test
    public void test1(){

        /*
        Given accept type is application/json
        And Path param is 2
        When user send Request /student/{id}
        Then status code should be 200
        And content type is application/json;charset=UTF-8
        and Date header is exist
        And server header is envoy
        And verify following:
        firstName: "Mark"
        batch: 13
        major: "math"
        "emailAddress": "mark@email.com"
        "companyName": "Cydeo"
        "street": "777 5th Ave"
        zipCode": 33222

         */


        //Given accept type is application/json
        //        And Path param is 2
        //        When user send Request /student/{id}
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("id",2).when().get("/student/{id}");
        response.prettyPrint();

        //Then status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        //And content type is application/json;charset=UTF-8
        Assertions.assertEquals("application/json;charset=UTF-8",response.getContentType());
//
//        //and Date header is exist
        Assertions.assertTrue(response.headers().toString().contains("date"));
//
//        //        And server header is envoy
        Assertions.assertEquals("envoy", response.headers().getValue("server"));

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("students[0].firstName");
        Assertions.assertEquals("Mark",firstName);


        int batch = jsonPath.getInt("students[0].batch");
        Assertions.assertEquals(13,batch);

        String major = jsonPath.getString("students[0].major");
        Assertions.assertEquals("math",major);

        String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
        Assertions.assertEquals("mark@email.com",emailAddress);

        String companyName = jsonPath.getString("students[0].company.companyName");
        Assertions.assertEquals("Cydeo",companyName);

        String street = jsonPath.getString("students[0].company.address.street");
        Assertions.assertEquals("777 5th Ave",street);

        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
//
        System.out.println(firstName);
        System.out.println(batch);
        System.out.println(major);
        System.out.println(emailAddress);
        System.out.println(street);
        System.out.println(zipCode);






    }

    @DisplayName("")
    @Test
    public void test2(){

//        RestAssured.given().accept(ContentType.JSON).




    }





}
