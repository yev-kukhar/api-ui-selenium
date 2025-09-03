package org.yevhenii.api_test.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.yevhenii.api.ApiEngine;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * <a href="https://github.com/typicode/json-server">...</a>
 *
 *   id will assign automatically
 *
 *   GET: Go to http://localhost:3000/posts in your browser to see your data.
 *   POST: To send a POST request to http://localhost:3000/posts with a new post object in the body. The new post will be added to your db.json file.
 *   PUT: Send a PUT request to http://localhost:3000/posts/1 to update the post with ID 1. The changes will be saved to your file
 *
 */
public class JsonServer extends ApiEngine {
    public JsonServer() {
        super("jsonserver.api.uri");
    }

    public Response getRecords() {
        return given()
                .spec(getRequestSpec())
                .get("/posts");
    }

    public Response getRecordById(String recordId) {
        return given()
                .spec(getRequestSpec())
                .get("/posts/" + recordId)
                .then()
                .extract().response();
    }

    public String postRecord(Map<String, String> newBook) {
        return given()
                .spec(getRequestSpec())
                .body(newBook)
                .post("/posts")
                .then()
                .statusCode(201)
                .extract().body().jsonPath().get("id");
    }


    public Response putRecord(String recordId, Map<String, String> updatedBook) {
        return given()
                .spec(getRequestSpec())
                .body(updatedBook)
                .log().all()
                .when()
                .put("/posts/" + recordId);
    }


    public Response patchRecord(String recordId, Map<String, String> updatedBook) {
        return given()
                .spec(getRequestSpec())
                .body(updatedBook)
                .patch("/posts/" + recordId);
    }

    public Response deleteRecord(String recordId) {
        return given()
                .spec(getRequestSpec())
                .delete("/posts/" + recordId);
    }
}

