package org.yevhenii.api_test;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.yevhenii.api_test.models.typicode.AddressModel;
import org.yevhenii.api_test.models.typicode.OrderModel;
import org.yevhenii.api_test.models.typicode.User;
import org.yevhenii.api_test.services.ApiServiceTypicode;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TypicodeTest {
    private ApiServiceTypicode apiServiceTypicode;


    @BeforeEach
    void setupServices() {
        apiServiceTypicode = new ApiServiceTypicode();
    }

    @Test
    @Tag("critical")
    void shouldReturnOnlyActiveOrdersTest() {
        Allure.step("Step 1: Receiving all resources");
        Response response = apiServiceTypicode.getListingAllResources();
        List<Map<String, Object>> posts = response.jsonPath().getList("$");

        Allure.step("Step 2: Check if the response is a list of size 100");
        assertEquals(100, posts.size());

        Allure.step("Check if all posts have non-null title, body, and userId");
        for (Map<String, Object> post : posts) {
            assertNotNull(post.get("userId"));
            assertNotNull(post.get("title"));
            assertNotNull(post.get("body"));
        }

        Allure.step("Check if all id values are unique");
        Set<Integer> ids = new HashSet<>();
        for (Map<String, Object> post : posts) {
            ids.add((Integer) post.get("id"));
        }
        assertEquals(ids.size(), posts.size());


        Allure.step("Check a specific post (e.g., post with id = 10 has correct title");
        Map<String, Object> postWithId10 = posts.stream()
                .filter(post -> ((Integer) post.get("id")) == 10)
                .findFirst()
                .orElse(null);

        Allure.step("Check all title fields are strings and not empty");
        for (Map<String, Object> post : posts) {
            String title = (String) post.get("title");
            assertNotNull(title);
            assertFalse(title.trim().isEmpty());
        }

        assertNotNull(postWithId10);
        assertEquals(postWithId10.get("title"), "optio molestias id quia eum");

    }

    @Test
    void shouldCreateUserWithNestedOrdersAndAddress() {

        Allure.step("Step 1: Build the request payload using POJOs (clean & maintainable)");
        AddressModel address = AddressModel.builder()
                .city("NYC")
                .country("USA")
                .build();

        List<OrderModel> orders = Arrays.asList(
                OrderModel.builder().id("O100").status("ACTIVE").amount(150.0).build(),
                OrderModel.builder().id("O101").status("CANCELLED").amount(99.99).build(),
                OrderModel.builder().id("O102").status("ACTIVE").amount(200.0).build(),
                OrderModel.builder().id("O103").status("PENDING").amount(75.0).build()
        );

        Long id = System.currentTimeMillis();
        String userName = "John Doe " + id;
        String userEmail = "john" + id+ "@example.com";

        User newUser = User.builder()
                .userId(id)
                .name(userName)
                .age(30)
                .email(userEmail)
                .active(true)
                .address(address)
                .orders(orders)
                .build();

        Allure.step("Step 2: Send POST request with serialized JSON body");
        Response response = apiServiceTypicode.createNewResource(newUser);

        Allure.step("Step 3: Validate the response contains the correct user email");
        String createdUserEmail = response.getBody().as(User.class).getEmail();
        assertEquals(userEmail, createdUserEmail,
                "The email returned in the response does not match the expected user email");
    }

}
