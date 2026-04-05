package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserData;
import models.ListingData;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class ApiClient {

    private static final String BASE_URL = "https://qa-desk.stand.praktikum-services.ru/api";

    static {
        RestAssured.baseURI = BASE_URL;
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
    }

    public static UserData registerUser(String email, String password) {
        System.out.println("User: " + email);

        String body = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"submitPassword\":\"%s\"}",
                email, password, password
        );

        Response response = given()
                .contentType("application/json")
                .body(body)
                .post("/signup")
                .then().statusCode(201).extract().response();

        UserData user = new UserData();
        user.setId(response.jsonPath().getInt("user.id"));
        user.setEmail(response.jsonPath().getString("user.email"));
        user.setName(response.jsonPath().getString("user.name"));
        user.setAccessToken(response.jsonPath().getString("access_token.access_token"));

        return user;
    }

    public static ListingData createListing(String token, String name) {
        System.out.println("Listing: " + name);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "multipart/form-data; boundary=---boundary")
                .multiPart("name", name)
                .multiPart("category", "Авто", "text/plain; charset=UTF-8")
                .multiPart("condition", "Новый", "text/plain; charset=UTF-8")
                .multiPart("city", "Москва", "text/plain; charset=UTF-8")
                .multiPart("description", "")
                .multiPart("price", "0")
                .log().all()
                .post("/create-listing");

        ListingData listing = new ListingData();
        listing.setId(response.jsonPath().getInt("id"));
        listing.setName(response.jsonPath().getString("name"));
        listing.setOwnerId(response.jsonPath().getInt("owner"));

        return listing;
    }

    public static void deleteListing(String token, int id) {
        System.out.println("Delete listing: " + id);
        try {
            given()
                    .header("Authorization", "Bearer " + token)
                    .delete("/listings/" + id)
                    .then().statusCode(200);
        } catch (AssertionError e) {
            System.out.println("Delete listing failed: " + e.getMessage());
        }
    }
}
