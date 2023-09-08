package com.otelrezervasyonu.tests;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
public class DeleteBookingTests extends BaseTest{


    @Test
    public void deleteBookingTest(){

        //Token Oluşturma
        // Rezervasyon Oluşturma
        // DELETE Çağrısı

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .when()
                .delete("/booking/" + createBookingId());

        // Assertions Yazma
        response
                .then()
                .statusCode(201);
    }
}
