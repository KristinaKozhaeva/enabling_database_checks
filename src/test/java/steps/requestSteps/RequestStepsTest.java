package steps.requestSteps;

import entity.Books;
import groovyjarjarasm.asm.TypeReference;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.requests.RequestGetBooks;
import models.requests.RequestGetBooksXML;
import models.requests.RequestSaveBooks;
import models.responses.ResponseGetBooks;
import models.responses.ResponseGetBooksXML;
import models.responses.ResponseSaveBooks;
import entity.Authors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Library Service Tests")
@Story("Get and Save Books")
public class RequestStepsTest {
    @Test
    @Description("Получение всех книг автора в формате JSON")
    @DisplayName("Получение книг автора (успешный запрос)")
    public void testGetBooksByAuthor() {
        RequestGetBooks request = new RequestGetBooks();
        request.setAuthorId("8");

        Response response = RequestSteps.getBooksByAuthor(request);

        ResponseGetBooks responseGetBooks = response.as(ResponseGetBooks.class);
        List<Books> books = responseGetBooks.getBooks();

        assertNotNull(books);
        assertTrue(books.size() > 0);
    }

    @Test
    @Description("Сохранение новой книги автора. Успешный запрос")
    @DisplayName("Сохранение книги автора")
    public void testSaveBook() {
        Authors author = new Authors();
        author.setId(88);
        author.setFirstName("Фёдор");
        author.setFamilyName("Достоевкий");
        author.setSecondName("Михайлович");

        RequestSaveBooks request = new RequestSaveBooks();
        request.setBookTitle("Преступление и наказание");
        request.setAuthor(author);

        Response response = RequestSteps.saveBook(request);

        ResponseSaveBooks responseSaveBooks = response.as(ResponseSaveBooks.class);

        assertTrue(responseSaveBooks.getBookId() > 0);
        assertEquals(0, responseSaveBooks.getErrorCode());
        assertNull(responseSaveBooks.getErrorMessage());
    }

    @Test
    @Description("Получение всех книг автора в формате XML")
    @DisplayName("Получение книг автора (XML. Успешный запрос)")
    public void testGetBooksByAuthorXML() {
        Authors author = new Authors();
        author.setId(8);

        RequestGetBooksXML request = new RequestGetBooksXML();
        request.setAuthor(author);

        Response response = RequestSteps.getBooksByAuthorXML(request);

        ResponseGetBooksXML responseGetBooksXML = response.as(ResponseGetBooksXML.class);
        List<Books> books = responseGetBooksXML.getBooks();

        assertNotNull(books);
        assertTrue(books.size() > 0);
    }


}