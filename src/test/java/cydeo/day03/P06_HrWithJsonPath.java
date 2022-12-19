package cydeo.day03;

import cydeo.Utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class P06_HrWithJsonPath extends HrTestBase {

    @DisplayName("GET All Countries")
    @Test
    public void test(){

        Response response = RestAssured.get("/countries");

//        response.prettyPrint();

        Assertions.assertEquals(200,response.statusCode());

        //Let's creat a json path
        JsonPath jsonPath = response.jsonPath();

        //Get me 3rd country name
        String country_3rd = jsonPath.getString("items[2].country_name");
        System.out.println("jsonpath.getString = " + country_3rd);

        //Get me 3rd country information
        String thirdCountryInfo = jsonPath.getString("items[2]");
        System.out.println(thirdCountryInfo);

        //Get me 3rd and 4th country at the same time
        String countryName3And4 = jsonPath.getString("items[2,3].country_name");
        System.out.println(countryName3And4);

        //Get me all country name where region_id is 2
        List<Integer> regionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(regionId2);

    }





}
