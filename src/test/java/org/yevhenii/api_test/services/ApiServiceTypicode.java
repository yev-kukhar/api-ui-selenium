package org.yevhenii.api_test.services;

import io.restassured.response.Response;
import org.yevhenii.api.ApiEngine;
import org.yevhenii.api_test.models.typicode.User;

import static io.restassured.RestAssured.given;


/**
 *  Target service: <a href="https://jsonplaceholder.typicode.com">...</a>
 *  endpoints:
 *     /posts/1/comments
 *     / albums/1/photos
 *     / users/1/albums
 *     / users/1/todos
 *     / users/1/posts
 */
public class ApiServiceTypicode extends ApiEngine {

    private static final String RESOURCES = "/posts";
    private static final String RESOURCES_PUT = "/posts/1";

    public ApiServiceTypicode() {
        super("typicode.uri");
    }

    public Response getListingAllResources() {
        return given()
                .spec(getRequestSpec())
                .get(RESOURCES)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response updateResource(User userModel) {
        return given()
                .spec(getRequestSpec())
                .body(userModel) // use the model as JSON body
                .put(RESOURCES_PUT)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response createNewResource(User newUser) {

        return given()
                .spec(getRequestSpec())
                .body(newUser)
                .when()
                .post(RESOURCES)
                .then()
                .statusCode(201) // or 200 depending on API
                .extract()
                .response();

    }

    public Response getPhotosFromAlbum(int albumId) {
        return given()
                .spec(getRequestSpec())
                .get(String.format(RESOURCES, albumId))
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getAlbumsForUser(int userId) {
        return given()
                .spec(getRequestSpec())
                .get(String.format(RESOURCES, userId))
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getTodosForUser(int userId) {
        return given()
                .spec(getRequestSpec())
                .get(String.format(RESOURCES, userId))
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getPostsForUser(int userId) {
        return given()
                .spec(getRequestSpec())
                .get(String.format(RESOURCES, userId))
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
