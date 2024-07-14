package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.requests.*;
import steps.requestSteps.RequestSteps;
import utils.Credentials;

public class RequestBuilder {

    private static final String BASE_URL = "http://localhost:8080/library";
    private static final String AUTHORIZATION_URL = "http://localhost:8080";

    private static RequestSpecBuilder baseSpecBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .addHeader("Authorization", "Bearer " + RequestSteps.getAuthorizationToken())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());
    }

    public static RequestSpecification requestGetBookSpec(RequestGetBooks requestGetBooks) {
        return baseSpecBuilder()
                .setBasePath(String.format("authors/%s/books", requestGetBooks.getId()))
                .addHeader("Accept", "application/json")
                .build();
    }

    public static RequestSpecification requestPostBookSpecXML(RequestPostBooksXML requestPostBooksXML) {
        return baseSpecBuilder()
                .setBasePath("authors/books")
                .setContentType(ContentType.XML)
                .setAccept(ContentType.XML)
                .setBody(requestPostBooksXML)
                .build();
    }

    public static RequestSpecification requestSaveBookSpec(RequestSaveBooks requestSaveBooks) {
        return baseSpecBuilder()
                .setBasePath("books/save")
                .setBody(requestSaveBooks)
                .build();
    }

    public static RequestSpecification requestSaveAuthorSpec(RequestSaveAuthors requestSaveAuthors){
        return baseSpecBuilder()
                .setBasePath("authors/save")
                .setBody(requestSaveAuthors)
                .build();
    }

    public static RequestSpecification requestAuthTokenSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(AUTHORIZATION_URL)
                .setBasePath("auth/login")
                .setContentType(ContentType.JSON)
                .setBody(String.format("{\"login\": \"%s\", \"password\": \"%s\"}", Credentials.getLogin(), Credentials.getPassword()))
                .build();
    }
}