package com.api.get;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.logging.Level;

import static io.restassured.RestAssured.given;

public class GetRequestTests
{
  @BeforeSuite
    void setUp(){}
    @Test
    @Owner("Hager")
    @Description("Verify users Data")
    void getRequest(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api/users";
        given().when().get().then().log().all().statusCode(200);
    }

    @Test
    void getRequest2(){
        RestAssured.baseURI="https://reqres.in/api/users";

        given().
                queryParam("page", "1")
                        .body("")
                .when()
                .get()
                .then()
                .assertThat().statusCode(200);

    }
   //JSON Path Example
    @Test
    void getResponseData(){
        String url = "https://reqres.in/api/users/1";

        Response response = RestAssured.given().get(url).andReturn();
        JsonPath jPath = response.jsonPath();
        String result = jPath.get("result");
        System.out.println(result);

    }
    @AfterSuite
    void tearDown(){}
}
