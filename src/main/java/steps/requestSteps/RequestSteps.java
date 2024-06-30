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

    public static ResponseGetBooks getBooksByAuthor(RequestGetBooks requestGetBooks) {
        return given()
                .spec(RequestBuilder.requestGetBookSpec(requestGetBooks))
                .get()
                .as(ResponseGetBooks.class);
    }

    public static ResponsePostBooksXML getBooksByAuthorXML(RequestPostBooksXML requestPostBooksXML) {
        return given()
                .spec(RequestBuilder.requestPostBookSpecXML(requestPostBooksXML))
                .post()
                .as(ResponsePostBooksXML.class);
    }

    public static ResponseSaveBooks saveBook(RequestSaveBooks requestSaveBooks) {
        return given()
                .spec(RequestBuilder.requestSaveBookSpec(requestSaveBooks))
                .post()
                .as(ResponseSaveBooks.class);
    }
}

