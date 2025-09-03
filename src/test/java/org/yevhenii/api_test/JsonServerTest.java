package org.yevhenii.api_test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

import org.yevhenii.api_test.services.JsonServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.yevhenii.Utils.getCurrentMillis;

public class JsonServerTest {

    private static JsonServer jsonServer;

    @BeforeAll
    public static void setUp() {
        jsonServer = new JsonServer();
    }

    @Test
    public void getRecordsAndCheckNotEmptyTest() {
        Response records = jsonServer.getRecords();
        System.out.println("Presented records are: \n" + records.prettyPrint());
    }

    @Test
    public void postRecordAndCheckThatCreatedTest() {

        // alternative way but make sure id will not return null with lombok

//        Book newBook = Book.builder()
//                .id(String.valueOf(id))
//                .title("great title " + id)
//                .author("Sir Arthur Conan Doyle")
//                .build();

        Map<String, String> newBook = new HashMap<>();
        newBook.put("title", "great title " + getCurrentMillis());
        newBook.put("author", "Sir Arthur Conan Doyle");

        String recordId = jsonServer.postRecord(newBook);
        System.out.println("Created record: " + recordId);

        List<String> ids = jsonServer.getRecords().then().extract().body().jsonPath().get("id");
        Assertions.assertTrue(ids.contains(recordId));
    }

    @Test
    public void putRecordAndVerifyUpdatedTest() {
        // 1. Create a new record and get its ID
        Map<String, String> newBook = new HashMap<>();
        newBook.put("title", "great title " + getCurrentMillis());
        newBook.put("author", "Sir Arthur Conan Doyle");
        String recordId = jsonServer.postRecord(newBook);

        // 2. Prepare the updated record data (with the same ID)
        Map<String, String> updatedBook = new HashMap<>();
        updatedBook.put("id", recordId); // The ID is required for PUT
        updatedBook.put("title", "updated title");
        updatedBook.put("author", "updated author");

        // 3. Perform the PUT request
        Response putResponse = jsonServer.putRecord(recordId, updatedBook);
        putResponse.then().statusCode(200);

        // 4. Verify the record was updated with a GET request
        Response getResponse = jsonServer.getRecordById(recordId);
        getResponse.then()
                .statusCode(200)
                .body("title", equalTo("updated title"))
                .body("author", equalTo("updated author"));
    }

    @Test
    public void patchRecordAndVerifyPartialUpdate() {
        // 1. Create a new record and get its ID
        Map<String, String> newBook = new HashMap<>();
        newBook.put("title", "original title" + getCurrentMillis());
        newBook.put("author", "original author");
        String recordId = jsonServer.postRecord(newBook);

        // 2. Prepare the partial update data
        Map<String, String> partialUpdate = new HashMap<>();
        partialUpdate.put("title", "patched title"); // Only send the fields we want to change

        // 3. Perform the PATCH request
        Response patchResponse = jsonServer.patchRecord(recordId, partialUpdate);
        patchResponse.then().statusCode(200);

        // 4. Verify the record was partially updated
        Response getResponse = jsonServer.getRecordById(recordId);
        getResponse.then()
                .statusCode(200)
                .body("title", equalTo("patched title"))
                .body("author", equalTo("original author")); // The author field should be unchanged
    }

    @Test
    public void deleteRecordAndVerifyRemoval() {
        // 1. Create a new record and get its ID
        Map<String, String> newBook = new HashMap<>();
        newBook.put("title", "to be deleted");
        newBook.put("author", "anonymous");
        String recordId = jsonServer.postRecord(newBook);

        // 2. Perform the DELETE request
        jsonServer.deleteRecord(recordId)
                .then().statusCode(200);

        // 3. Verify the record is gone with a GET request
        Response getResponse = jsonServer.getRecordById(recordId);
        getResponse.then().statusCode(404); // Expect a 404 Not Found status
    }
}
