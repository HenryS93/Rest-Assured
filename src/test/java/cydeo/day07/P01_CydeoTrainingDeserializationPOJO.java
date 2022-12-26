package cydeo.day07;

import static io.restassured.RestAssured.*;


import cydeo.Utilities.CydeoTrainingTestBase;
import static org.hamcrest.Matchers.*;

import cydeo.pojo.StudentPractice;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTrainingTestBase {



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

    @DisplayName("GET /student/{id} 2")
    @Test
    public void test1(){


        JsonPath jsonPath = given().accept(ContentType.JSON).pathParam("id", 2)
                .when().get("/student/{id}").prettyPeek()
                .then().statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .header("Date",notNullValue())
                .header("server",is("envoy"))
                .extract().jsonPath();


        StudentPractice studentWithId2 = jsonPath.getObject("students[1]", StudentPractice.class);
        System.out.println("studentWithId2 = " + studentWithId2);

    }

}
