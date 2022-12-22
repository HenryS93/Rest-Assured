package cydeo.practice;

import cydeo.Utilities.CydeoTrainingTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class practice2 extends CydeoTrainingTestBase {

    @Test
    public void test2() {
        /*
        TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
         */

        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("batch", 22).when().get("/student/batch/{batch}");
        response.prettyPrint();



        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        Assertions.assertEquals("application/json;charset=UTF-8",response.getContentType());


        Assertions.assertTrue(response.headers().toString().contains("date"));


        //And Server header is envoy
        String value = response.headers().getValue("server");
        Assertions.assertEquals("envoy",value);


        JsonPath jsonPath = response.jsonPath();

        List<Integer> idList = jsonPath.getList("students.batch");
        for (Integer each: idList){
            Assertions.assertTrue(each==22);
            System.out.println(each);
        }
    }


}
