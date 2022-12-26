package cydeo.day06;

import cydeo.Utilities.HrTestBase;
import static io.restassured.RestAssured.*;

import cydeo.pojo.Employee;
import cydeo.pojo.Link;
import cydeo.pojo.Region;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P03_DeserializationPOJO extends HrTestBase {


    @DisplayName("GET All Regions to deserialization to POJO + LOMBOK + JSON Property")
    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions").then()
                .statusCode(200)
                .extract().jsonPath();

            //WITHOUT @JSONPROPERTY ANNOTATION FROM JACKSON LIBRARY
//        Region firstRegion = jsonPath.getObject("items[0]", Region.class);
//        System.out.println("firstRegion = " + firstRegion);
//
//        Link firstRegionLink = firstRegion.getLinks().get(0);
//        System.out.println("firstRegionLink = " + firstRegionLink);
//
//        String firstRegionName = firstRegion.getRegion_name();
//        System.out.println("firstRegionName = " + firstRegionName);


        Region fromFirstRegion = jsonPath.getObject("items[0]", Region.class);
        System.out.println("FirstRegion.getRegionName() = " + fromFirstRegion.getRegionName());  ///Getting regionName from HRTesBase from element[0]

        System.out.println("=============================================================================================");

        //GETTING AS A LIST<LINK> returns all info inside the array

        Link linkFrom0Element = fromFirstRegion.getLinks().get(0);    // as a List<link> from element[0]
        System.out.println("linkFrom0Element = " + linkFrom0Element); // Result: ->>>   linkFrom0Element = Link(rel=self, href=http://3.86.105.252:1000/ords/hr/regions/1)

        System.out.println("=============================================================================================");

        String href = fromFirstRegion.getLinks().get(0).getHref(); // We can be more specific and get only HREF OR REL as a single Object
        System.out.println("href = " + href); // RESULT: --->> href = http://3.86.105.252:1000/ords/hr/regions/1

        System.out.println("=============================================================================================");


    }

    @DisplayName("GET Employees to deserialization to POJO with required fields")
    @Test
    public void test2() {

        JsonPath jsonPath = get("/employees").then()
                .statusCode(200)
                .extract().jsonPath();


        Employee employeeElement0 = jsonPath.getObject("items[0]", Employee.class);
        System.out.println("employeeElement0 = " + employeeElement0);


    }
}
