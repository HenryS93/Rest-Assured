package cydeo.day06;

import cydeo.Utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P01_HRDeserilization extends HrTestBase {

    /**
     *
     * Create a test called getlocation
     * 1.Send request to GET / locations
     * 2.log uri to see
     * 3.get all json as a map and print out screen all the things with the help of map
     * System.out.println("============== GET FIRST LOCATION ============== " );
     * System.out.println("============== GET FIRST LOCATION LINKS ============== " );
     * System.out.println("============== GET ALL LOCATIONS AS A LIST OF MAPS ============== " );
     * System.out.println("============== FIRST LOCATION ============== " );
     * System.out.println("============== GET FIRST LOCATION ID ============== " );
     * System.out.println("============== GET FIRST LOCATION FIRST LINK ============== " );
     * System.out.println("============== LAST LOCATION ID ============== " );
     */

    @DisplayName("GET Locations From HR")
    @Test
    public void getLocation(){

        JsonPath jsonPath = given().log().uri().when().get("/locations").then().statusCode(200).extract().response().jsonPath();


        System.out.println("============== GET FIRST LOCATION ============== " );
        Map<String, Object> pathMap = jsonPath.getMap("items[0]");
        System.out.println("pathMap = " + pathMap);

        System.out.println("============== GET FIRST LOCATION LINKS ============== " );
        Map<String, Object> firstLink = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstLink = " + firstLink);
        //To get href value
        System.out.println("firstLink.get(\"href\") = " + firstLink.get("href"));


        System.out.println("============== GET ALL LOCATIONS AS A LIST OF MAPS ============== " );
        System.out.println("LIST:");
        List<Map<String,Object>> allLocationMap = jsonPath.getList("items");
        System.out.println("allLocationMap = " + allLocationMap);

        for (Map<String,Object> eachmap : allLocationMap){
            System.out.println("eachmap = " + eachmap);
        }

         System.out.println("============== FIRST LOCATION_ID FROM ALL LOCATIONS ============== " );
         System.out.println(allLocationMap.get(0).get("location_id"));

        System.out.println("============== GET FIRST LOCATION COUNTRY_ID ============== " );
        System.out.println(allLocationMap.get(0).get("country_id"));

        System.out.println("============== GET FIRST LOCATION FIRST LINK ============== " );
        System.out.println(" allLocationMap.get(0).get(\"link[0]\")= " +  allLocationMap.get(0).get("links"));

        List<Map<String,Object>> links = (List<Map<String, Object>>) allLocationMap.get(0).get("links");
        System.out.println(links.get(0).get("href"));

        System.out.println("========================================LAST LOCATION ID FROM ALL LOCATIONS==================================");
        System.out.println("allLocationMap.get(allLocationMap.size()-1).get(\"location_id\")= "+allLocationMap.get(allLocationMap.size()-1).get("location_id"));













    }














}
