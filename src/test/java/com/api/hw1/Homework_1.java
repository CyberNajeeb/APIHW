package com.api.hw1;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class Homework_1 {

    @BeforeAll
    public static void setup() {
        baseURI = "https://cybertek-ui-names.herokuapp.com/api/";
    }

    /*
    TEST CASES
No params test
1. Send a get request without providing any parameters
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that name, surname, gender, region fields have value
     */
    @Test
    @DisplayName("No param test")
    public void testCase_1() {
        Response response = given().get().prettyPeek();
        response.then().statusCode(200).
                and().
                contentType("application/json; charset=utf-8").
                and().
                body("name", notNullValue()).
                and().
                body("surname", notNullValue()).
                and().
                body("gender", notNullValue()).
                and().
                body("region", notNullValue());
    }

    /*
    Gender test
1. Create a request by providing query parameter: gender, male or female
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that value of gender field is same from step 1
     */
    @Test
    @DisplayName("Gender test")
    public void genderTest() {
        List<String> genders = Arrays.asList("male", "female");
        String gender = genders.get(1);
        Collections.shuffle(genders);
        Response response = given().
                queryParams("gender", gender).when().get().prettyPeek();
        response.then().statusCode(200).
                and().
                contentType("application/json; charset=utf-8").
                and().
                body("gender", is(gender));
    }
    /*
    2 params test
1. Create a request by providing query parameters: a valid region and gender
NOTE: Available region values are given in the documentation
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that value of gender field is same from step 1
4. Verify that value of region field is same from step 1
     */
    @Test
    @DisplayName("Gender test")
    public void twoParamTest() {
        Response response = given().queryParams("France", "male").when().get().prettyPeek();
    }
}
