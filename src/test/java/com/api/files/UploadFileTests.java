package com.api.files;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class UploadFileTests {

@Test
public void uploadImage (){
    URL resourceUrl = getClass().getClassLoader().getResource("src/main/resources/Screenshot.png");

    File fileToUpload = new File(resourceUrl.getPath());

   Response response= RestAssured.given()
            .multiPart("file", fileToUpload)
            .post("https://the-internet.herokuapp.com/upload")
            .thenReturn();
}

}
