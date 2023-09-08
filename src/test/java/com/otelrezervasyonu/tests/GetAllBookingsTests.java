package com.otelrezervasyonu.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests extends BaseTest{
// Çağrıyı Oluşturmak
// Response Kontrolleri
//curl -i https://restful-booker.herokuapp.com/booking

    @Test
    public void getAllBookingTest(){

        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }

    @Test
    public void getBookings_with_firstname_filter_test(){
            // Yeni rezervasyon oluştur

            int bookingId = createBookingId();

            // İnt bookingid = kısmına bir rezevasyon numarası atıyarak o rezervasyonun listemize olup olmadığını görüntüleyebiliriz

            //Çağrımıza Query parametresi ekle

            spec.queryParam("firstname","Serdal");
            spec.queryParam("lastname","Akyol");

            // Çağrıyı gerçekleştir
                Response response = given(spec)
                        .when()
                        .get("/booking");

            // Assertion Test Yaz

        response
                .then()
                .statusCode(200);

        List<Integer> filtrelenenRezervasyon = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filtrelenenRezervasyon.contains(bookingId));
    }
}


