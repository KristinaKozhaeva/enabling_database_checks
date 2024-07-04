package testAPIMethod;

import configuration.RequestBuilder;
import entity.Authors;
import entity.Books;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.requests.RequestGetBooks;

import models.requests.RequestPostBooksXML;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.GetBookAssertions;
import steps.requestSteps.RequestSteps;
import utils.DataHelper;

import java.util.List;

import static io.restassured.RestAssured.given;
import static steps.asserts.GetBookAssertions.checkStatusCode;


@Epic("Запросы на получение книг")
@Story("Получение книг автора")
public class GetBooksStepsTest {

    @Test
    @DisplayName("Получение книг автора. Позитивный тест")
    @Description("Книги автора успешно получены")
    public void testGetBooksByAuthor() {
        RequestGetBooks requestGetBooks = new RequestGetBooks();
        requestGetBooks.setAuthorId("12");

        List<Books> booksList = RequestSteps.getBooksByAuthor(requestGetBooks);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(booksList);
    }

    @Test
    @DisplayName("Получение книг в формате XML. Позитивный тест")
    @Description("Получены книги в формате XML")
    public void testBooksByAuthorPostXML() {
        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        Authors author = new Authors();
        author.setId(66);
        requestPostBooksXML.setAuthor(author);

        List<Books> booksList = RequestSteps.getBooksByAuthorXML(requestPostBooksXML);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(booksList);
    }


    @Test
    @DisplayName("Получение книг не сохраненного автора в формате json. Негативный тест")
    @Description("Сервис возвращает http код ошибки = 400 с описанием: “Указанный автор не существует в таблице”")
    public void successGetBooksByAuthor() {

        RequestGetBooks requestGetBooks = new RequestGetBooks();
        requestGetBooks.setAuthorId("888");

        Response response = given()
                .spec(RequestBuilder.requestGetBookSpec(requestGetBooks))
                .get();

        GetBookAssertions.checkStatusCode(response, 400);
    }

    @Test
    @DisplayName("Получение книг несохраненного автора в формате xml. Негативный тест")
    @Description("Сервис возвращает Http код ошибки = 400 с описанием: “Указанный автор не существует в таблице”")
    public void testGetBookUnsavedAuthorXML() {
        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        Authors author = new Authors();
        author.setId(888);
        requestPostBooksXML.setAuthor(author);

        Response response = given()
                .spec(RequestBuilder.requestPostBookSpecXML(requestPostBooksXML))
                .post();

        checkStatusCode(response, 400);

    }


    @Test
    @DisplayName("Получение книг автора с незаполненным ID автора json. Негативный тест")
    @Description("Сервис возвращает http код ошибки = 400")
    public void testGetBookWithoutId() {

        RequestGetBooks requestGetBooks = new RequestGetBooks();

        Response response = given()
                .spec(RequestBuilder.requestGetBookSpec(requestGetBooks))
                .get();

        checkStatusCode(response, 400);

    }

    @Test
    @DisplayName("Получение книг автора с незаполненным ID автора xml. Негативный тест")
    @Description("Сервис возвращает http код ошибки = 400")
    public void testGetBookWithoutIdXML() {
        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        Authors author = new Authors();
        requestPostBooksXML.setAuthor(author);

        Response response = given()
                .spec(RequestBuilder.requestPostBookSpecXML(requestPostBooksXML))
                .post();

        checkStatusCode(response, 400);

    }

    @Test
    @DisplayName("Получение книг автора, у которого нет сохраненных книг json. Позитивный тест")
    @Description("Возвращается пустой список и статус 200")
    void testGetBooksByAuthorWithoutBooks() {
        Authors author = DataHelper.getSavedAuthorWithoutBooks();

        RequestGetBooks requestGetBooks = new RequestGetBooks();

        requestGetBooks.setAuthorId(String.valueOf(author.getId()));

        List<Books> booksList = RequestSteps.getBooksByAuthor(requestGetBooks);

        Response response = given()
                .spec(RequestBuilder.requestGetBookSpec(requestGetBooks))
                .get();
        GetBookAssertions.checkStatusCode(response, 200);

        GetBookAssertions.assertBooksListIsEmpty(booksList);

    }

    @Test
    @DisplayName("Получение книг автора, у которого нет сохраненных книг xml. Позитивный тест")
    @Description("Если у автора нет книг, должен вернуться пустой список и статус 200")
    void testGetBooksByAuthorWithoutBooksXML() {
        Authors author = DataHelper.getSavedAuthorWithoutBooksXML();

        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        requestPostBooksXML.setAuthor(author);

        List<Books> booksList = RequestSteps.getBooksByAuthorXML(requestPostBooksXML);

        Response response = given()
                .spec(RequestBuilder.requestPostBookSpecXML(requestPostBooksXML))
                .post();
        GetBookAssertions.checkStatusCode(response, 200);

        GetBookAssertions.assertBooksListIsEmpty(booksList);
    }

}