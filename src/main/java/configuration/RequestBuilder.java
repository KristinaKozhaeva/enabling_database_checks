package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    private static final String BASE_URL = "http://localhost:8080/library";

    public static RequestSpecBuilder specBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setContentType(ContentType.JSON);
    }

    public static RequestSpecification requestGetBookSpec() {
        return specBuilder()
                .build();
    }

    public static RequestSpecification requestPostBookSpecXML() {
        return specBuilder()
                .setContentType(ContentType.XML)
                .build();
    }

    public static RequestSpecification requestSaveBookSpec() {
        return specBuilder()
                .build();
    }

}
