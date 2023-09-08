package com.otelrezervasyonu.tests;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartiallyUptdateBookingTests extends BaseTest {


    @Test
    public void partiallyUptadeBookingTest(){

        //Token oluştur
        // Rezervasyon Oluştur
        // Çağrıyı yap

        JSONObject body = new JSONObject();
        body.put("firstname","Ali Asaf");


        Response response  = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .body(body.toString())
                .when()
                .patch(" /booking/" + createBookingId());



        //Assertions(Testleri) Yaz

        Assertions.assertEquals("Ali Asaf", response.jsonPath().getJsonObject("firstname"));

    }


}
