package testAPIMethod;

import configuration.RequestBuilder;
import io.qameta.allure.Description;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.requests.RequestSaveBooks;
import entity.Authors;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.responses.ResponseSaveBooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.SaveBooksAssertions;
import utils.DataHelper;

import static io.restassured.RestAssured.given;
import static steps.requestSteps.RequestSteps.saveBook;

@Epic("Запросы на сохранение книг")
@Story("Сохранение книг автора")
public class PostBooksStepsTest {

    @Test
    @DisplayName("Сохранение новой книги")
    @Description("Новая книга автора успешно сохранена. Позитивный тест")
    public void testSaveNewBookTest() {
        Authors author = new Authors();
        author.setId(88);

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Преступление и наказание");
        requestSaveBooks.setAuthor(author);

        ResponseSaveBooks responseSaveBooks = saveBook(requestSaveBooks);

        SaveBooksAssertions.assertResponseSaveBooks(responseSaveBooks);
    }

    @Test
    @DisplayName("Сохранение новой книги без названия. Негативный тест")
    @Description("Сервис возвращает ошибку “Не передан обязательный параметр” и Http код = 400")
    public void testSaveBookWithoutTitle() {
        Authors author = DataHelper.getSavedAuthor();

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle(null);
        requestSaveBooks.setAuthor(author);

        Response response = given()
                .spec(RequestBuilder.requestSaveBookSpec(requestSaveBooks))
                .post();

        SaveBooksAssertions.checkStatusCodeForSave(response, 400);
    }


    @Test
    @DisplayName("Сохранение новой книги не сохраненного автора. Негативный тест")
    @Description("Сервис возвращает ошибку: “Указанный автор не существует в таблице” и Http код = 400")
    public void testSaveBookWithUnsavedAuthor() {
        Authors unregisteredAuthor = DataHelper.getUnsavedAuthor();

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Test Book Title");
        requestSaveBooks.setAuthor(unregisteredAuthor);

        Response response = given()
                .spec(RequestBuilder.requestSaveBookSpec(requestSaveBooks))
                .post();

        SaveBooksAssertions.checkStatusCodeForSave(response, 409);
    }

    @Test
    @DisplayName("Сохранение новой книги без автора. Негативный тест")
    @Description("Сервис возвращает ошибку и http код = 400")
    public void testSaveBookWithoutAuthors() {

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("преступление и наказание");
        requestSaveBooks.setAuthor(null);

        Response response = given()
                .spec(RequestBuilder.requestSaveBookSpec(requestSaveBooks))
                .post();

        SaveBooksAssertions.checkStatusCodeForSave(response, 400);
    }

    @Test
    @DisplayName("Сохранение новой книги без ID автора. Негативный тест")
    @Description("Сервис возвращает ошибку и http код = 400")
    public void testSaveBookWithoutAuthorsID() {

        RequestSaveBooks book = DataHelper.getBookWithoutAuthorId();
        RequestSpecification requestSpec = RequestBuilder.requestSaveBookSpec(book);

        Response response = given()
                .spec(requestSpec)
                .post();

        SaveBooksAssertions.checkStatusCodeForSave(response, 400);
    }
}

