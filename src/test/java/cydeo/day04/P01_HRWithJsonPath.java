package cydeo.day04;

import cydeo.Utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class P01_HRWithJsonPath extends HrTestBase {


    @DisplayName("Get All employees with Query Param limit = 200 --> JSONPATH")
    @Test
    public void test1(){

        /*
        Given accept type is application/json
        and Query param limit is 200
        When user send GET request / employees
        Then user should see
         */

        Response response = RestAssured.given().accept(ContentType.JSON).and()
                .queryParam("limit", 200).when().get("/employees");

//        response.prettyPrint();

        //Status code 200
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);

        //Get all emails from response
        List<String> allEmails =response.path("items.email");
        System.out.println(allEmails); // -> all emails from total employees -> 107
        System.out.println(allEmails.size()); //-> how many total employees = 107

        System.out.println("___________________________________________________________________________");
        //Get all emails who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();
        List<String> It_ProgList = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.email");
        System.out.println(It_ProgList);
        System.out.println(It_ProgList.size());

        System.out.println("___________________________________________________________________________");


        //Get me all employees first_name whose salary is more than 10000

        List<String> salaryMoreThan10000 = jsonPath.getList("items.findAll{it.salary>=10000}.first_name");
        System.out.println(salaryMoreThan10000);

        System.out.println("___________________________________________________________________________");


        //Get me all information from response who has max salary
        System.out.println(jsonPath.getString("items.max{it.salary}"));

        System.out.println("___________________________________________________________________________");


        //Get me firstname from response who has max salary
        System.out.println(jsonPath.getString("items.max{it.salary}.first_name"));

        System.out.println("___________________________________________________________________________");


        //Get me first_name whose salary is the lowest
        String firstNameWithLowestSalary = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println(firstNameWithLowestSalary);

        System.out.println("___________________________________________________________________________");



    }







}
