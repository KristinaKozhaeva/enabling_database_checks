package steps.asserts;

import entity.Books;
import io.restassured.response.Response;
import models.responses.ResponseSaveBooks;
import steps.requestSteps.RequestSteps;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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

    public static void asserErrorMessage(Response response, String expectedMessage) {
        String actualMessage = RequestSteps.getErrorMessage(response);
        assertThat(actualMessage, equalTo(new String(expectedMessage.getBytes(), StandardCharsets.UTF_8)));
    }

    public static void asserErrorMessageXML(Response response, String expectedMessage) {
        String actualMessage = RequestSteps.getErrorMessageXML(response);
        assertThat(actualMessage, equalTo(new String(expectedMessage.getBytes(), StandardCharsets.UTF_8)));
    }

    public static void assertBooksMatchAuthor(List<Books> books, long authorId) {
        assertNotNull(books);
        assertTrue(books.size() > 0);

        for (Books book : books) {
            assertNotNull(book);
            assertNotNull(book.getAuthorID());
            assertEquals(book.getAuthorID().getId(), authorId);
            assertNotNull(book.getBookTitle());
        }
    }
}
