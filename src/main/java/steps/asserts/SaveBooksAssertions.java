package steps.asserts;

import models.responses.ResponseSaveBooks;

import static org.junit.jupiter.api.Assertions.*;

public class SaveBooksAssertions {

    public static void assertResponseSaveBooks(ResponseSaveBooks response) {
        assertTrue(response.getBookId() > 0);
        assertEquals(0, response.getErrorCode());
        assertNull(response.getErrorMessage());
    }
}
