package org.core.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestApi {

    public static Response post(String uRI, String stringJSON){
        RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.post(uRI);
        return response;
    }

    public static Response get(String uRI){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.get(uRI);
        return response;
    }

    public static Response put(String uRI,String stringJSON){
        RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.put(uRI);
        return response;
    }

    public static Response delete(String uRI){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.delete(uRI);
        return response;
    }


    public static Response postCallWithToken(String uRI,String stringJSON, String token){
        RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.headers("Authorization",token);
        Response response = requestSpecification.post(uRI);
        return response;
    }
}
