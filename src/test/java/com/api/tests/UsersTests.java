package com.api.tests;

import static org.hamcrest.Matchers.*;

import com.api.utils.Config;
import com.api.utils.DateUtils;
import com.api.utils.JsonFileManager;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


public class UsersTests extends CommonSpecs {
    private JsonFileManager jsonFileManager = new JsonFileManager("data/test_data.json");

    @Description("This a test for getting all the users data")
    @Test
    public void getUsersTest() {
        Response response = RestAssured.
                given()
                .spec(requestSpec)
                .basePath(Config.USERS_BASE_PATH)
                .when()
                .get();
        response.getBody().prettyPrint();
        response.then()
                .spec(successResponseSpec);
    }
    @Description("This a test for getting all the users data with delay")
    @Test
    public void getUsersTestWithDelay() {
        Response response = RestAssured.
                given()
                .spec(requestSpec)
                .basePath(Config.USERS_BASE_PATH)
                .queryParam("delay", jsonFileManager.getInt("users.delay_seconds"))
                .when()
                .get();
        response.getBody().prettyPrint();
        response.then()
                .spec(successResponseSpec)
                .body("page", equalTo(jsonFileManager.getInt("users.page")))
                .body("data[0].first_name", equalTo(jsonFileManager.getString("users.expected_first_user")));
    }
    @Description("This is a test for creating new user")
    @Test
    public void createNewUserTest() {
        String userData = String.format("{" + "\"name\": \"%s\",\n" +
                "\"job\": \"%s\"" + "}",
                jsonFileManager.getString("registration.name"),
                jsonFileManager.getString("registration.job")
                );
        Response response = RestAssured
                .given()
                .spec(requestSpec)
                .basePath(Config.USERS_BASE_PATH)
                .body(userData)
                .when()
                .post();

        response.then()
                .statusCode(jsonFileManager.getInt("registration.expected_status"))
                .body("id", notNullValue())
                .body("name", equalTo(jsonFileManager.getString("registration.name")))
                .body("job", equalTo( jsonFileManager.getString("registration.job")));
        String createdAt = response.jsonPath().getString("createdAt");
        DateUtils.validateCreatedAtDate(createdAt);
    }
}