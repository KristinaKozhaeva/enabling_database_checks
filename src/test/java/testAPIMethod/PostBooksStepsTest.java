package testAPIMethod;

import entity.Books;
import io.qameta.allure.Description;

import io.restassured.response.Response;
import models.requests.RequestSaveBooks;
import entity.Authors;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.responses.ResponseSaveBooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.LibraryDatabaseAssertions;
import steps.asserts.SaveBooksAssertions;
import steps.dataBaseSteps.DatabaseOperations;
import steps.requestSteps.RequestSteps;
import utils.DataHelper;

import java.util.List;

import static steps.requestSteps.RequestSteps.saveBook;
import static utils.ErrorMessages.*;


@Epic("Запросы на сохранение книг")
@Story("Сохранение книг автора")
public class PostBooksStepsTest {

    private final DatabaseOperations databaseOperations = new DatabaseOperations();

    @BeforeEach
    public void clearDatabase() {
        databaseOperations.deleteAll();
    }

    @Test
    @DisplayName("Сохранение новой книги")
    @Description("Новая книга автора успешно сохранена. Позитивный тест")
    public void testSaveNewBookTest() {
        Authors author = DataHelper.getSavedAuthor();

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Преступление и наказание");
        requestSaveBooks.setAuthor(author);

        ResponseSaveBooks responseSaveBooks = saveBook(requestSaveBooks);

        Assertions.assertNotNull(responseSaveBooks);

        SaveBooksAssertions.assertResponseSaveBooks(responseSaveBooks);

        List<Books> dbBooks = databaseOperations.findByBookTitle("Преступление и наказание");
        LibraryDatabaseAssertions.assertBookSaved(dbBooks, "Преступление и наказание");
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

        List<Books> dbBooks = databaseOperations.findByBookTitle(null);
        LibraryDatabaseAssertions.assertBooksListIsEmpty(dbBooks);
    }

    @Test
    @DisplayName("Сохранение новой книги не сохраненного автора. Негативный тест")
    @Description("Сервис возвращает ошибку: 'Указанный автор не существует в таблице' и Http код = 409")
    public void testSaveBookWithUnsavedAuthor() {
        Authors unregisteredAuthor = DataHelper.getUnsavedAuthor();

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Преступление и наказание");
        requestSaveBooks.setAuthor(unregisteredAuthor);

        Response response = RequestSteps.saveBookAndGetResponse(requestSaveBooks);

        SaveBooksAssertions.checkStatusCodeForSave(response, 409);

        SaveBooksAssertions.checkErrorMessageForSave(response, AUTHOR_NOT_FOUND);

        List<Books> dbBooks = databaseOperations.findByBookTitle("Преступление и наказание");
        LibraryDatabaseAssertions.assertBooksListIsEmpty(dbBooks);
    }

    @Test
    @DisplayName("Сохранение новой книги без автора. Негативный тест")
    @Description("Сервис возвращает ошибку и http код = 400")
    public void testSaveBookWithoutAuthors() {

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Преступление и наказание");
        requestSaveBooks.setAuthor(null);

        Response response = RequestSteps.saveBookAndGetResponse(requestSaveBooks);

        SaveBooksAssertions.checkStatusCodeForSave(response, 400);

        SaveBooksAssertions.checkErrorMessageForSave(response, NO_AUTHOR);

        List<Books> dbBooks = databaseOperations.findByBookTitle("Преступление и наказание");
        LibraryDatabaseAssertions.assertBooksListIsEmpty(dbBooks);
    }

    @Test
    @DisplayName("Сохранение новой книги без ID автора. Негативный тест")
    @Description("Сервис возвращает ошибку и http код = 409")
    public void testSaveBookWithoutAuthorsID() {

        RequestSaveBooks book = DataHelper.getBookWithoutAuthorId();

        Response response = RequestSteps.saveBookAndGetResponse(book);

        SaveBooksAssertions.checkStatusCodeForSave(response, 409);

        SaveBooksAssertions.checkErrorMessageForSave(response, AUTHOR_NOT_FOUND);

        List<Books> dbBooks = databaseOperations.findByBookTitle(book.getBookTitle());
        LibraryDatabaseAssertions.assertBooksListIsEmpty(dbBooks);
    }
}

