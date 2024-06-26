package steps.requestSteps;

import configuration.RequestBuilder;
import models.requests.RequestGetBooks;
import models.requests.RequestPostBooksXML;
import models.requests.RequestSaveBooks;
import models.responses.ResponseGetBooks;
import models.responses.ResponsePostBooksXML;
import models.responses.ResponseSaveBooks;

import static io.restassured.RestAssured.given;

public class RequestSteps {

    public static ResponseGetBooks getBooksByAuthor(RequestGetBooks request) {
        return given()
                .spec(RequestBuilder.requestGetBookSpec())
                .body(request)
                .pathParam("authorId", request.getAuthorId())
                .when()
                .get("/authors/{authorId}/books")
                .as(ResponseGetBooks.class);
    }

    public static ResponsePostBooksXML getBooksByAuthorXML(RequestPostBooksXML requestPostBooksXML) {
        return given()
                .spec(RequestBuilder.requestPostBookSpecXML())
                .body(requestPostBooksXML)
                .when()
                .post("/authors/books")
                .as(ResponsePostBooksXML.class);
    }

    public static ResponseSaveBooks saveBook(RequestSaveBooks requestSaveBooks) {
        return given()
                .spec(RequestBuilder.requestSaveBookSpec())
                .body(requestSaveBooks)
                .when()
                .post("/books/save")
                .as(ResponseSaveBooks.class);
    }
}

