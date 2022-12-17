package cydeo.Utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://3.86.105.252:1000/ords/hr";

    }


}
