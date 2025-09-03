package org.yevhenii.api_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.yevhenii.api.models.privatbank.ExchangeRate;
import org.yevhenii.api.models.privatbank.MockUserPrivatbank;
import org.yevhenii.api.models.privatbank.ExchangeArchiveResponse;
import org.yevhenii.api_test.services.ApiServicePrivatbank;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Tag("api-tests")
@Tag("critical")
@Epic("API Testing")
@Feature("PrivatBank API")
@DisplayName("PrivatBank API Tests")
public class PrivatbankTest {

    private ApiServicePrivatbank privatbank;
    String date = "22.03.2025";
    Response exchangeRatesForDateResponse;

    @BeforeEach
    void setupServices() {
        privatbank = new ApiServicePrivatbank();
        exchangeRatesForDateResponse = privatbank.getExchangeRatesForDate(date);
    }

    @Test
    @Story("As a user, I can get exchange rates for a specific date")
    @Description("This test verifies the GET method and deserializes the JSON response.")
    void getExchangeRatesForDateTest() {
        String date = "22.03.2022";
        Response response = privatbank.getExchangeRatesForDate(date);

        response.then()
                .statusCode(200)
                .body("date", equalTo(date))
                .body("bank", equalTo("PB"))
                .body("exchangeRate", notNullValue());

        // --- Deserialization Method 1: Using the POJO class
        ExchangeArchiveResponse archiveResponse = response.as(ExchangeArchiveResponse.class);
        assertNotNull(archiveResponse);
        assertFalse(archiveResponse.getExchangeRate().isEmpty());

        // --- Deserialization Method 2: Using JSONPath
        JsonPath jsonPath = response.jsonPath();
        List<String> currencyList = jsonPath.getList("exchangeRate.currency");
        assertFalse(currencyList.isEmpty());
        System.out.println("Currencies found for " + date + ": " + currencyList);
    }

    @Test
    @Story("As a user, I can create a new resource")
    @Description("This test demonstrates a POST request and verifies the response.")
    void createNewUserTest() {
        MockUserPrivatbank newUser = MockUserPrivatbank.builder()
                .name("morpheus")
                .job("leader")
                .build();

        Response response = privatbank.createUser(newUser);

        response.then()
                .statusCode(201)
                .body("name", equalTo(newUser.getName()))
                .body("job", equalTo(newUser.getJob()))
                .body("id", notNullValue());

        // --- Deserialization Method 1: Using the POJO class
        MockUserPrivatbank createdUser = response.as(MockUserPrivatbank.class);
        assertNotNull(createdUser.getId());
    }

    @Test
    @Story("As a user, I can update an existing resource")
    @Description("This test demonstrates a PUT request and verifies the response.")
    void updateUserTest() {
        String userId = "2";
        MockUserPrivatbank userToUpdate = MockUserPrivatbank.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        Response response = privatbank.updateUser(userId, userToUpdate);

        response.then()
                .statusCode(200)
                .body("name", equalTo(userToUpdate.getName()))
                .body("job", equalTo(userToUpdate.getJob()));
    }


    //get unique currencies from the response "currency": "CAD",

    @Test
    public void getUniqueValuesUsingJsonPath() {
        System.out.println("=== METHOD 1: REST-ASSURED JSONPATH ===");
        List<String> currenicies = exchangeRatesForDateResponse.jsonPath()
                .getList("exchangeRate.currency");
        Set<String> unique = new LinkedHashSet<>(currenicies);
        System.out.println(unique);
    }

    @Test
    public void getUniqueValuesUsingJackson() throws JsonProcessingException {
        System.out.println("=== METHOD 2: JACKSON OBJECTMAPPER ===");

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = exchangeRatesForDateResponse.asString();

        JsonNode rootNode = mapper.readTree(jsonBody);
        JsonNode exchangeRates = rootNode.get("exchangeRate");

        Set<String> uniqueCurrencies = new LinkedHashSet<>();

        if (exchangeRates.isArray()) {
            for (JsonNode rate : exchangeRates) {
                String currency = rate.get("currency").asText();
                uniqueCurrencies.add(currency);
            }
        }

        System.out.println("Unique currencies count: " + uniqueCurrencies.size());
        System.out.println("Unique currencies: " + uniqueCurrencies);

        // Alternative: Using Jackson with streaming
        Set<String> streamUnique = new LinkedHashSet<>();
        exchangeRates.forEach(rate -> streamUnique.add(rate.get("currency").asText()));
        System.out.println("Stream approach: " + streamUnique.size() + " currencies");

    }

    @Test
    public void getUniqueValuesUsingStreams() {
        List<String> list = exchangeRatesForDateResponse.jsonPath().getList("exchangeRate.currency");
        Set<String> unique = list.stream().collect(Collectors.toSet());
    }

    @Test
    public void getUniqueValuesUsingPojo() {
        ExchangeArchiveResponse exchangeRateResponse = exchangeRatesForDateResponse.as(ExchangeArchiveResponse.class);
        Set<String> uniqueCurrencies = exchangeRateResponse.getExchangeRate().stream()
                .map(ExchangeRate::getCurrency)
                .collect(Collectors.toSet());
    }
}
