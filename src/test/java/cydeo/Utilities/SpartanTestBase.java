package cydeo.Utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {


 @BeforeAll
 public static void init(){

     RestAssured.baseURI="http://3.86.105.252:8000";

 }



}
