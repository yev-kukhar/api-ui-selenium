package org.yevhenii.api_test.services;

import io.restassured.response.Response;
import org.yevhenii.api.ApiEngine;
import org.yevhenii.api.models.privatbank.ExchangeArchiveResponse;
import org.yevhenii.api.models.privatbank.MockUserPrivatbank;

import static io.restassured.RestAssured.given;

public class ApiServicePrivatbank extends ApiEngine {
    private static final String USERS_PATH = "/users";
    private static final String EXCHANGE_ARCHIVE_PATH = "/exchange_rates";

    public ApiServicePrivatbank() {
        super("privatbank.api.uri");
//        System.getenv("PRIVATBANK_API_URI");
    }

    public Response createUser(MockUserPrivatbank user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .post(USERS_PATH);
    }

    public Response updateUser(String userId, MockUserPrivatbank user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .put(USERS_PATH + "/" + userId);
    }


    public Response getExchangeRatesForDate(String date) {
        return given()
                .spec(getRequestSpec())
                .queryParam("json")
                .queryParam("date", date)
                .get(EXCHANGE_ARCHIVE_PATH);
    }

    public ExchangeArchiveResponse getExchangeRatesAsObject(String date) {
        return getExchangeRatesForDate(date)
                .then()
                .statusCode(200)
                .extract()
                .as(ExchangeArchiveResponse.class);
    }
}
