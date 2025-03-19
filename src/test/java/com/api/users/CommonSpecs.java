package com.api.users;

import com.api.utils.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;


public class CommonSpecs {
    protected RequestSpecification requestSpec;
    protected ResponseSpecification successResponseSpec;
    @BeforeSuite
    public void setUp(){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Config.BASE_URL)
                .setBasePath("api/users")
                .setContentType("application/json")
                .build();

        successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();

    }
}
