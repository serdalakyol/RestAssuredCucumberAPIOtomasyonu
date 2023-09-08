package com.otelrezervasyonu.tests;
import com.otelrezervasyonu.models.Booking;
import com.otelrezervasyonu.models.BookingDates;
import com.otelrezervasyonu.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class CreateBookingTests extends BaseTest{

    @Test
    public void createBookingTest(){

        //Çağrı Gerçekleştir

       Response response= createBooking();

        Assertions.assertEquals("Serdal",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Akyol",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,(Boolean) response.jsonPath().getJsonObject("booking.depositpaid"));

        //Assertionsları Yaz


    }
    @Test
    public void createBookingWithPojo(){
        BookingDates bookingDates = new BookingDates("2024-08-10","2024-08-25");
        Booking booking = new Booking("Selda","Akyol",500,false,bookingDates,"an air-conditioned room");

        // Resonse
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        //Assertion

        response
                .then()
                .statusCode(200);


        BookingResponse bookingResponse = response.as(BookingResponse.class);

        System.out.println(bookingResponse + "Booking Response Kaydedildi");

        //Assertions

        Assertions.assertEquals("Selda",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Akyol",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("an air-conditioned room",bookingResponse.getBooking().getAdditionalneeds());
    }

}
