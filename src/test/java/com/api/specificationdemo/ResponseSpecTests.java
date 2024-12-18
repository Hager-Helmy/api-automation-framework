package com.api.specificationdemo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ResponseSpecTests {
    @BeforeSuite
    void setUp(){}

    static RequestSpecification getCommonSpecRequest(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://reqres.in");
        builder.setBasePath("/api/users");
        RequestSpecification requestSpecification = builder.build();
        return requestSpecification;
    }

    static ResponseSpecification getCommonSpecResponse(){
       ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        ResponseSpecification responseSpecification = builder.build();
        return responseSpecification;
    }

    @Test
    void testResponseSpecificationWithoutParams(){
        RestAssured.
                 given()
                .spec(getCommonSpecRequest())
                .when()
                .get()
                .then()
                .spec(getCommonSpecResponse());

    }

    @Test
    void testResponseSpecificationWithParams(){
      RestAssured.
                given()
                .spec(getCommonSpecRequest())
                .queryParam("page","1")
                .when()
                .get()
                .then()
               .spec(getCommonSpecResponse());
    }
}
