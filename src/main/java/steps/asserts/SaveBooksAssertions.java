package steps.asserts;

import io.restassured.response.Response;
import models.responses.ResponseSaveBooks;

import static org.junit.jupiter.api.Assertions.*;

public class SaveBooksAssertions {

    public static void assertResponseSaveBooks(ResponseSaveBooks response) {
        assertTrue(response.getBookId() > 0);
        assertEquals(0, response.getErrorCode());
        assertNull(response.getErrorMessage());
    }

    public static void checkStatusCodeForSave(Response response, int errorCode) {
        response
                .then()
                .statusCode(errorCode);
    }
}
