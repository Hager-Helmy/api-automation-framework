package com.api.patch;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PatchUpdateUserTests {

    @Test
    void postRequest(){
        RestAssured.baseURI="https://reqres.in/api/users";

        String userData = "{" + "\"first_name\": \"Traceyyy\",\n" +
                "            \"last_name\": \"Ramos\"" + "}";

        given()
                .body(userData)
                .when()
                .post()
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .body("id",(notNullValue()));
    }
}
