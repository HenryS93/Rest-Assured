package cydeo.day05;

import cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P04_DeserilizationToCollection extends SpartanTestBase {

    @Test
    public void test1() {

        /*
        Given accept type is application/json
        And Path param id = 10
        When I send Get request to /api/spartans
        Then Status code should be 200
        And Content type is json
        And spartan data matching
                id= 10
                name= Lorenza
                gender= Female
                phone= 3312820936
         */
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 10).when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        //Approach one --> with response
        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);

        // --> outpu: java.lang.IllegalStateException: Cannot parse object because no JSON deserializer found in classpath. Please put either Jackson (Databind) or Gson in the classpath.

        /*
        We need to use either Jackson or Gson to resolve this problem
         */


        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");
        String gender = (String) spartanMap.get("gender");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);


        //SECOND APPROACH

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> jsonMap = jsonPath.getMap("");
        System.out.println("jsonMap = " + jsonMap);

        int id1 = (int) jsonMap.get("id");
        String name1 = (String) jsonMap.get("name");
        String gender1 = (String) jsonMap.get("gender");

        System.out.println("id1 = " + id1);
        System.out.println("name1 = " + name1);
        System.out.println("gender1 = " + gender1);

    }


    @DisplayName("GET All Spartans with Java Collections")
    @Test
    public void test2() {


        Response response = given().accept(ContentType.JSON).when().get("/api/spartans/")
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response();


        List<Map<String, Object>> spartanList = response.as(List.class);
        System.out.println("spartanList = " + spartanList);


        for (Map<String, Object> eachMap : spartanList) {
            System.out.println("eachMap = " + eachMap);
        }


            //how to find 1st spartan info
            System.out.println("spartanList.get(0)= " + spartanList.get(0));


            //how to find 1st spartan name
            String firstSpartanName = (String) spartanList.get(0).get("name");
            System.out.println("firstSpartanName = " + firstSpartanName);


            //how to find first spartan id
            int firstSpartanId = (int) spartanList.get(0).get("id");
            System.out.println("firstSpartanId= " + firstSpartanId);


            //how to store first spartan info as a map with response.as() method
            // if you wanna convert only specific part of response with response/as() it does NOT have any parameters to get as path of
            //json Object. that is why we can use in here response.path() method to convert this Object. Since we know the type of it
            //we can use and store as a MAP

            System.out.println(" ====================================================================================== ");
            //how to store first spartan info as a map with response.as() method
            Map<String, Object> firstSpartanMap = response.path("[0]");
            System.out.println("firstSpartanMap = " + firstSpartanMap);

            System.out.println(" ====================================================================================================");

            JsonPath jsonPath = response.jsonPath();
            List<Map<String, Object>> jsonPathList = jsonPath.getList("");

            for (Map<String, Object> eachSpartan : jsonPathList) {
                System.out.println("eachSpartan = " + eachSpartan);
            }

            //How to find first spartan info
            Map<String, Object> firstSpartan = jsonPathList.get(0);
            System.out.println("firstSpartan = " + firstSpartan);

            //How to find first spartan name
            String firstSpartanNameFromJsonPathList = (String) jsonPathList.get(0).get("name");
            System.out.println("firstSpartanNameFromJsonPathList = " + firstSpartanNameFromJsonPathList);

            //How to find first spartan id
        int idFromJsonPathList = (int) jsonPathList.get(0).get("id");
        System.out.println("idFromJsonPathList = " + idFromJsonPathList);


    }
}
