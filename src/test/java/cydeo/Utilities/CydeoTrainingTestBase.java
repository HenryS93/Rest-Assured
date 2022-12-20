package cydeo.Utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class CydeoTrainingTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://api.training.cydeo.com";
    }




}
