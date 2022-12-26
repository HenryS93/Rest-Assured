package cydeo.day06;

import cydeo.Utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P03_DeserializationPOJO extends HrTestBase {


    @DisplayName("GET All Regions to deserialization to POJO + LOMBOK + JSON Property")
    @Test
    public void test1(){

        RestAssured.given().accept(ContentType.JSON).when().get("/regions")






    }








}
