package org.yevhenii.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.yevhenii.ConfigReader;

public class ApiEngine {

    private final ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();

    public ApiEngine(String uriKey) {

        if (uriKey == null || uriKey.trim().isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() +
                    ": URI key must not be null or empty");
        }

        String baseUri = ConfigReader.getProperty(uriKey);

        RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
        requestSpec.set(spec);
    }

    protected RequestSpecification getRequestSpec() {
        return requestSpec.get();
    }
}
