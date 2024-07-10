package steps.asserts;

import entity.Authors;
import io.restassured.response.Response;
import models.responses.ResponseSaveBooks;
import steps.requestSteps.RequestSteps;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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

    public static void checkErrorMessageForSave(Response response, String expectedMessage) {
        String actualMessage = RequestSteps.getErrorMessage(response);
        assertThat(actualMessage, equalTo(new String(expectedMessage.getBytes(), StandardCharsets.UTF_8)));
    }

    public static void assertAuthorResponse(Authors expected, Authors actual) {
        assertNotNull(actual);
        assertTrue(actual.getId() > 0);
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getFamilyName(), actual.getFamilyName());
        assertEquals(expected.getSecondName(), actual.getSecondName());
    }
}
