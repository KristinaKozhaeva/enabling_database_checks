package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.requests.RequestGetBooks;
import models.requests.RequestPostBooksXML;
import models.requests.RequestSaveAuthors;
import models.requests.RequestSaveBooks;

public class RequestBuilder {

    private static final String BASE_URL = "http://localhost:8080/library";

    private static RequestSpecBuilder baseSpecBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());
    }

    public static RequestSpecification requestGetBookSpec(RequestGetBooks requestGetBooks) {
        return baseSpecBuilder()
                .setBasePath(String.format("authors/%s/books", requestGetBooks.getAuthorId()))
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
}
