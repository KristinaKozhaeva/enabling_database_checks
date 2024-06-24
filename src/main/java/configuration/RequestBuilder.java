package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    private static final String BASE_URL = "http://localhost:8080/library";

    public static RequestSpecBuilder specBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON);
    }

    public static RequestSpecification requestGetBookSpec() {
        return specBuilder()
                .build();

    }

    public static RequestSpecification requestGetBookSpecXML() {
        return specBuilder()
                .setContentType(ContentType.XML)
                .build();
    }

    public static RequestSpecification requesSaveBook() {
        return specBuilder()
                .build();

    }

}
