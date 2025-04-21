package com.api.tests;

import com.api.utils.Config;
import com.api.utils.JsonFileManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;


public class CommonSpecs {
    protected RequestSpecification requestSpec;
    protected ResponseSpecification successResponseSpec;
    private JsonFileManager jsonFileManager = new JsonFileManager("data/test_data.json");

    @BeforeClass
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Config.BASE_URL)
                .setContentType(jsonFileManager.getString("login.successful.content_type"))
                .build();

        successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(jsonFileManager.getInt("login.successful.expected_status"))
                .expectContentType(jsonFileManager.getString("login.successful.content_type"))
                .build();

    }
}
