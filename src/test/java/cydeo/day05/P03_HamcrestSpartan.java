package cydeo.day05;

import cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class P03_HamcrestSpartan extends SpartanTestBase {

    @DisplayName("GET Single Spartan With Hemcrest")
    @Test
    public void test1(){

        /*
        Given accept type is json
        And path param id is 15
        When user sends a get request to spartans/{id}
        Then status code is 200
        And content type is Json
        and Json data has following
        name = "Meta"
        gender = "Female"
        phone = "1938695106"
         */

        //Log().all() -> wil display all info about my request AFTER: given() from start

        //RESPONSE
        //Then().log().all()  ->> it will give all information about your request (path. query params, URI , Body ect
        //ifValidateFails()  ->>  it will print all response if one of the validation FAILS

        JsonPath jsonPath = given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}").prettyPeek()
                .then().log().ifValidationFails().statusCode(200)
                .and().contentType("application/json")
//                .assertThat() // these are again syntetic sugar just to increase readability
                .and().body("id", is(15), "name", is("Meta"), "gender", is("Female"), "phone", is(1938695106))
                .extract().response().jsonPath();

        //ANSWERS WITH RESPONSE VARIABLE
//        int id = response.path("id");
//        System.out.println("id = " + id);
//
//        String name = response.path("name");
//        System.out.println("name = " + name);
//
//        String gender = response.path("gender");
//        System.out.println("gender = " + gender);
//
//
//        int phone = response.path("phone");
//        System.out.println("phone = " + phone);

        //ANSWERS WITH JSONPATH() AFTER RESPONSE

        int id = jsonPath.getInt("id");
        System.out.println("id = " + id);


            /* How to print RESPONSE BODY
            -response.prettyPrint();  --> it is printing response body into screen
            -response.prettyPeek(); --> it will response into screen, returns and allow us to continue chaining

             */

        /*
        HOW TO EXTRACT DATA AFTER DOING VALIDATION WITH HAMCREST
        -> extract()  --> this method will help us to get data after doing verification as
                response()
                jasonPath()

          Why extract?
          -> Assume that we are gonna do verification against UI/DB. In that case i need to get data from API after doing verification.
          - So we need to sometimes List of Names / ids ect to check
          - That is why we need to initialize as Response or JsonPath (*Since we know how to get data through this objects)
          to get related data that you wanna verify
        */



    }





}
