package com.otelrezervasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UptadeBookingTests extends BaseTest{

    @Test
    public void uptadeBookingTest(){






        //Rezervasyon Oluştur

        //Request Yap

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .body(bookingObject("Merve","Duygu",500,true))
                .put(" /booking/" + createBookingId());



        //Assertion/Test Yaz

        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        Integer totalPrice = response.jsonPath().getJsonObject("totalprice");
        Boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");

        Assertions.assertEquals("Merve",firstName);
        Assertions.assertEquals("Duygu",lastName);
        Assertions.assertEquals(500,totalPrice);
        Assertions.assertEquals(true,depositPaid);

    }



}
