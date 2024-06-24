package steps.requestSteps;

import configuration.RequestBuilder;
import io.restassured.response.Response;
import models.requests.RequestGetBooks;
import models.requests.RequestGetBooksXML;
import models.requests.RequestSaveBooks;

import static io.restassured.RestAssured.given;

public class RequestSteps {

    public static Response getBooksByAuthor(RequestGetBooks request) {
        return given()
                .spec(RequestBuilder.requestGetBookSpec())
                .body(request)
                .pathParam("authorId", request.getAuthorId())
                .when()
                .get("/authors/{authorId}/books");
    }

    public static Response getBooksByAuthorXML(RequestGetBooksXML requestGetBooksXML) {
        return given()
                .spec(RequestBuilder.requestGetBookSpecXML())
                .body(requestGetBooksXML)
                .when()
                .get("/authors/books");
    }

    public static Response saveBook(RequestSaveBooks requestSaveBooks) {
        return given()
                .spec(RequestBuilder.requesSaveBook())
                .body(requestSaveBooks)
                .when()
                .post("/books/save");
    }
}

