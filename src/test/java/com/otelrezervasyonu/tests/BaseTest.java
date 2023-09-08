package com.otelrezervasyonu.tests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {

    RequestSpecification spec ;
    //RequestSpecification Method Yazımı

    @BeforeEach
    public void setup(){

         spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter() , new ResponseLoggingFilter()))
                .build();


    }
    protected int createBookingId(){

        Response response = createBooking();
        return  response.jsonPath().getJsonObject("bookingid");
    }

    protected Response createBooking(){

        //Çağrı Gerçekleştir

        Response response=  given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Serdal","Akyol",200,true).toString())
                .post("/booking");


        response
                .then()
                .statusCode(200);
        return response;
    }

    protected String bookingObject(String firstname , String lastname , int totalprice,boolean depositPaid){
        JSONObject body = new JSONObject();
        body.put("firstname",firstname);
        body.put("lastname",lastname);
        body.put("totalprice",totalprice);
        body.put("depositpaid",true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2024-01-01");
        bookingDates.put("checkout","2024-01-07");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Pet Allowed Room");

        return body.toString();
    }
    //Token Oluştur
    public String createToken(){

        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .post("/auth");

        return response.jsonPath().getJsonObject("token");

    }
}
