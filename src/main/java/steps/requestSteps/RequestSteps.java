package steps.requestSteps;

import configuration.RequestBuilder;
import entity.Books;
import io.restassured.response.Response;
import models.requests.RequestGetBooks;
import models.requests.RequestPostBooksXML;
import models.requests.RequestSaveAuthors;
import models.requests.RequestSaveBooks;
import models.responses.ResponseSaveAuthors;
import models.responses.ResponseSaveBooks;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.parsing.Parser.JSON;

public class RequestSteps {

    public static List<Books> getBooksByAuthor(RequestGetBooks requestGetBooks) {
        return given()
                .spec(RequestBuilder.requestGetBookSpec(requestGetBooks))
                .get()
                .then()
                .parser("application/json", JSON)
                .extract()
                .jsonPath()
                .getList(".", Books.class);
    }

    public static List<Books> getBooksByAuthorXML(RequestPostBooksXML requestPostBooksXML) {
        return given()
                .spec(RequestBuilder.requestPostBookSpecXML(requestPostBooksXML))
                .post()
                .then()
                .extract()
                .xmlPath()
                .getList(".", Books.class);
    }

    public static ResponseSaveBooks saveBook(RequestSaveBooks requestSaveBooks) {
        return given()
                .spec(RequestBuilder.requestSaveBookSpec(requestSaveBooks))
                .post()
                .as(ResponseSaveBooks.class);
    }

    public static ResponseSaveAuthors saveAuthor(RequestSaveAuthors requestSaveAuthor)  {
        return given()
                .spec(RequestBuilder.requestSaveAuthorSpec(requestSaveAuthor))
                .post()
                .as(ResponseSaveAuthors.class);
    }

    public static Response saveBookAndGetResponse(RequestSaveBooks requestSaveBooks) {
        return given()
                .spec(RequestBuilder.requestSaveBookSpec(requestSaveBooks))
                .post();
    }

    public static Response getBooksByAuthorAndGetResponse(RequestGetBooks requestGetBooks) {
        return given()
                .spec(RequestBuilder.requestGetBookSpec(requestGetBooks))
                .header("Authorization", "Bearer " + getAuthorizationToken())
                .get();
    }

    public static Response getBooksByAuthorXMLAndGetResponse(RequestPostBooksXML requestPostBooksXML) {
        return given()
                .spec(RequestBuilder.requestPostBookSpecXML(requestPostBooksXML))
                .post();
    }


    public static String getErrorMessage(Response response) {
        return response
                .then()
                .extract()
                .path("errorMessage");
    }

    public static String getErrorMessageXML(Response response) {
        return response
                .then()
                .extract()
                .xmlPath()
                .getString("errorMessage");
    }

    public static String getAuthorizationToken() {
        Response response = given()
                .spec(RequestBuilder.requestAuthTokenSpec())
                .get();

        System.out.println("Response body: " + response.getBody().asString());

        return response
                .jsonPath()
                .getString("jwtToken");
    }
}