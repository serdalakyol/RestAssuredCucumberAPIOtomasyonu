package com.otelrezervasyonu.tests;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest {

    @Test
    public void getBookingById(){

 //Çağrıyı Oluşturmamız
 //Response Kontrolleri

     //   Response newBooking = createBooking();
     //   int reservastionId = newBooking.jsonPath().getJsonObject("bookingid");

        Response response =  given(spec)
                .when()
                .get("/booking/" + createBookingId());
            response.then()
                    .statusCode(200);



        String firstname=  response.jsonPath().getJsonObject("firstname");
        String lastname= response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");


        Assertions.assertEquals("Serdal",firstname);
        Assertions.assertEquals("Akyol",lastname);
        Assertions.assertEquals(200,totalprice);


    }
}
