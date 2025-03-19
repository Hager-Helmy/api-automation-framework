package com.api.users;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;



public class UsersTests extends CommonSpecs {

    @Description("This a test for getting all the users data")
    @Test
    public void getUsersTest() {
        Response response = RestAssured.
                given()
                .spec(requestSpec)
                .when()
                .get();
        response.getBody().prettyPrint();
        response.then()
                .spec(successResponseSpec);
    }

    @Description("This is a test for creating new user")
    @Test
    public void createNewUserTest(){
        String userData = "{" + "\"first_name\": \"Hager\",\n" +
                "            \"last_name\": \"Ahmed\"" + "}";
        Response response = RestAssured
                .given()
                .spec(requestSpec)
                .body(userData)
                .when()
                .post();

                response.then()
                .statusCode(201)
                .body("id", (notNullValue()));
    }
}