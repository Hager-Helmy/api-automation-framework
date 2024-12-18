package com.api.specificationdemo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RequestSpecTests {
    @BeforeSuite
    void setUp(){}

    static RequestSpecification getCommonSpecRequest(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://reqres.in");
        builder.setBasePath("/api/users");
        RequestSpecification requestSpecification = builder.build();
        return requestSpecification;
    }

    @Test
    void testRequestSpecificationWithoutParams(){
        Response response = RestAssured.
                 given()
                .spec(getCommonSpecRequest())
                .when()
                .get();
        response.getBody().prettyPrint();
    }

    @Test
    void testRequestSpecificationWithParams(){
        Response response = RestAssured.
                given()
                .spec(getCommonSpecRequest())
                .queryParam("page","1")
                .when()
                .get();
        response.getBody().prettyPrint();
    }
}
