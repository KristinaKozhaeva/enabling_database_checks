package testAPIMethod;

import io.qameta.allure.Description;

import io.restassured.response.Response;
import models.requests.RequestSaveBooks;
import entity.Authors;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.responses.ResponseSaveBooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.SaveBooksAssertions;
import steps.requestSteps.RequestSteps;
import utils.DataHelper;

import static steps.requestSteps.RequestSteps.saveBook;
import static utils.ErrorMessages.*;


@Epic("Запросы на сохранение книг")
@Story("Сохранение книг автора")
public class PostBooksStepsTest {

    @Test
    @DisplayName("Сохранение новой книги")
    @Description("Новая книга автора успешно сохранена. Позитивный тест")
    public void testSaveNewBookTest() {
        Authors author = DataHelper.getSavedAuthor();

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Преступление и наказание");
        requestSaveBooks.setAuthor(author);

        ResponseSaveBooks responseSaveBooks = saveBook(requestSaveBooks);

        Assertions.assertNotNull(responseSaveBooks, "Response is null");

        SaveBooksAssertions.assertResponseSaveBooks(responseSaveBooks);
    }

    @Test
    @DisplayName("Сохранение новой книги без названия. Негативный тест")
    @Description("Сервис возвращает ошибку 'Не передан обязательный параметр' и Http код = 400")
    public void testSaveBookWithoutTitle() {
        Authors author = DataHelper.getSavedAuthor();

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle(null);
        requestSaveBooks.setAuthor(author);

        Response response = RequestSteps.saveBookAndGetResponse(requestSaveBooks);

        SaveBooksAssertions.checkStatusCodeForSave(response, 400);

        SaveBooksAssertions.checkErrorMessageForSave(response, NO_BOOK_TITLE);
    }

    @Test
    @DisplayName("Сохранение новой книги не сохраненного автора. Негативный тест")
    @Description("Сервис возвращает ошибку: 'Указанный автор не существует в таблице' и Http код = 409")
    public void testSaveBookWithUnsavedAuthor() {
        Authors unregisteredAuthor = DataHelper.getUnsavedAuthor();

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Test Book Title");
        requestSaveBooks.setAuthor(unregisteredAuthor);

        Response response = RequestSteps.saveBookAndGetResponse(requestSaveBooks);

        SaveBooksAssertions.checkStatusCodeForSave(response, 409);

        SaveBooksAssertions.checkErrorMessageForSave(response, AUTHOR_NOT_FOUND);
    }

    @Test
    @DisplayName("Сохранение новой книги без автора. Негативный тест")
    @Description("Сервис возвращает ошибку и http код = 400")
    public void testSaveBookWithoutAuthors() {

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("преступление и наказание");
        requestSaveBooks.setAuthor(null);

        Response response = RequestSteps.saveBookAndGetResponse(requestSaveBooks);

        SaveBooksAssertions.checkStatusCodeForSave(response, 400);

        SaveBooksAssertions.checkErrorMessageForSave(response, NO_AUTHOR);
    }

    @Test
    @DisplayName("Сохранение новой книги без ID автора. Негативный тест")
    @Description("Сервис возвращает ошибку и http код = 409")
    public void testSaveBookWithoutAuthorsID() {

        RequestSaveBooks book = DataHelper.getBookWithoutAuthorId();

        Response response = RequestSteps.saveBookAndGetResponse(book);

        SaveBooksAssertions.checkStatusCodeForSave(response, 409);

        SaveBooksAssertions.checkErrorMessageForSave(response, AUTHOR_NOT_FOUND);
    }
}

