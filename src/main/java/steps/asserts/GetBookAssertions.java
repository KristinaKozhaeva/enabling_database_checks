package steps.asserts;

import entity.Books;
import io.restassured.response.Response;
import steps.requestSteps.RequestSteps;

import java.nio.charset.StandardCharsets;
import entity.Authors;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static utils.DataHelper.getExpectedBook;

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


    public static void assertBooksMatchAuthor(List<Books> actualBooks, Authors expectedAuthor) {
        assertNotNull(actualBooks);
        assertFalse(actualBooks.isEmpty());

        for (Books actualBook : actualBooks) {
            assertNotNull(actualBook);
            assertNotNull(actualBook.getId());

            Authors actualAuthorId = actualBook.getAuthorID();
            assertNotNull(actualAuthorId);

            assertEquals(expectedAuthor.getId(), actualAuthorId.getId());

            Books expectedBook = getExpectedBook(actualBook);

            assertEquals(expectedBook.getBookTitle(), actualBook.getBookTitle());
        }
    }
}
