package cydeo.day05;




import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class P01_HamCrestMatchersIntro {

    @Test
    public void numbers(){

        //It comes from Junit 5 to make assertions
        assertEquals(9,3+6);

        /*
        Hamcrest Matchers comes from RestAssured dependency
        import static org.hamcrest.Matchers.*;
        import static org.hamcrest.Matchers.*;
        Adding the following static import we are not gonna use classNames
        While we are calling method from related classes
        Matchers has 2 overloaded methods
        -1st one will take value to check
        -2nd one will take another Matcher to make it readable/ to add new assert functionality
         */

        //All the SAME function
        assertEquals(9,3+6);
        assertThat(9, Matchers.is(6+3));
        assertThat(9, is(equalTo(6+3)));

        /**
         * is(someValue)
         * is(equalTo(someValue)
         * All of them same in terms of assertions
         */

        assertThat(5+5, not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(5+5))));

        /**
         * These 3 are same again
         *
         */


        /**
         * greaterThan() lessThan()
         * greaterThanOrEqualTo() lessThanOrEqualTo()
         */

        assertThat(5+6,is(greaterThan(10)));
        assertThat(5+6,greaterThan(10));


    }

    @Test
    public void testStrings(){
        String msg = "API is fun!";

        //Equal to
        assertThat(msg, is("API is fun!"));// -> TRUE
        assertThat(msg,equalTo("API is fun!"));// -> TRUE
        assertThat(msg,equalToIgnoringCase("API is fun!"));// -> TRUE

        //starts with
        assertThat(msg,startsWith("API"));// -> TRUE
        assertThat(msg,startsWithIgnoringCase("api"));// -> TRUE

        //Ends with
        assertThat(msg,endsWith("fun!"));// -> TRUE
        assertThat(msg,endsWithIgnoringCase("FUN!"));// -> TRUE

        //contains
        assertThat(msg,containsString("is"));// -> TRUE
        assertThat(msg,containsStringIgnoringCase("IS"));// -> TRUE


        //is NOT equalTO
        assertThat(msg, not("Fun!"));// -> TRUE
        assertThat(msg, is(not("Fun!")));// -> TRUE

    }

    @Test
    public void testCollections(){


        List<Integer> numberList = Arrays.asList(3,5,1,77,44,76);

        //how to check the size of elements
        assertThat(numberList,hasSize(6));// -> TRUE

        //how to check 44 is into collection
        assertThat(numberList,hasItem(44));// -> TRUE

        //to make it FAIL!
        //assertThat(numberList,hasItem(2));


        //how to check 44 and 76 is into collection
        assertThat(numberList,hasItems(44,76)); //-> TRUE
        assertThat(numberList,hasItems(44,76,1));// -> TRUE
//        assertThat(numberList,hasItems(44,76,1,2));

        //check greater value than collection
        assertThat(numberList,hasItems(greaterThan(70)));

//        assertThat(numberList,everyItem(greaterThan(5))); //// -> FAIL! we have 1, 3 values which are NOT greater than 5

        //everyItem -> check each element into Array about related condition
        assertThat(numberList,everyItem(greaterThanOrEqualTo(1))); //-> TRUE

        assertThat(numberList,containsInRelativeOrder(3,5,1,77,44,76));








    }






}
