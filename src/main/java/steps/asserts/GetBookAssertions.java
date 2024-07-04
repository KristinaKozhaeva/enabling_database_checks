package steps.asserts;

import entity.Books;
import io.restassured.response.Response;
import models.responses.ResponseGetBooks;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetBookAssertions {

    public static void assertBooksListNotNullAndNotEmpty(List<Books> books) {
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    public static void checkStatusCode(Response response, int errorCode) {
        response
                .then()
                .statusCode(errorCode);
    }

    public static void assertBooksListIsEmpty(List<Books> books) {
        assertTrue(books.isEmpty());
    }

}
