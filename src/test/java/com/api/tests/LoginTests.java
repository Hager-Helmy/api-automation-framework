package com.api.tests;

import com.api.utils.Config;
import com.api.utils.JsonFileManager;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends CommonSpecs {
    private JsonFileManager jsonFileManager = new JsonFileManager("data/test_data.json");

    @Description("This a test for successful login")
    @Test
    public void testSuccessfulLogin() {
        String requestBody = String.format("{" + "\"email\": \"%s\",\n" +
                "\"password\": \"%s\" }",
                jsonFileManager.getString("login.successful.email"),
                jsonFileManager.getString("login.successful.password")
        );
        Response response = RestAssured.
                given()
                .spec(requestSpec)
                .basePath(Config.LOGIN_BASE_PATH)
                .body(requestBody)
                .when()
                .get();
        response.getBody().prettyPrint();
        response.then()
                .spec(successResponseSpec);
    }

    @Description("This a test for unsuccessful login")
    @Test
    public void testUnSuccessfulLogin() {
        String requestBody = String.format("{" + "\"email\": \"%s\"}",
                jsonFileManager.getString("login.unsuccessful.email")
                );
        Response response = RestAssured
                .given()
                .spec(requestSpec)
                .basePath(Config.LOGIN_BASE_PATH)
                .body(requestBody)
                .when()
                .post();
        response.then()
                .statusCode(jsonFileManager.getInt("login.unsuccessful.expected_status"))
                .body("error", equalTo(jsonFileManager.getString("login.unsuccessful.expected_error")));
    }
}