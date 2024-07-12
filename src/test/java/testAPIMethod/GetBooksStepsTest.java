package testAPIMethod;

import entity.Authors;
import entity.Books;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.requests.RequestGetBooks;

import models.requests.RequestPostBooksXML;
import models.responses.ResponsePostBooksXML;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.GetBookAssertions;
import steps.requestSteps.RequestSteps;
import utils.DataHelper;

import java.util.List;

import static steps.asserts.GetBookAssertions.checkStatusCode;
import static utils.ErrorMessages.AUTHOR_NOT_FOUND;
import static utils.ErrorMessages.INVALID_REQUIRED_PARAMETER;

@Epic("Запросы на получение книг")
@Story("Получение книг автора")
public class GetBooksStepsTest {

        @Test
        @DisplayName("Получение книг автора. Позитивный тест")
        @Description("Книги автора успешно получены")
        public void testGetBooksByAuthor() {
            Authors author = DataHelper.getSavedAuthor();
            Authors expectedAuthor = DataHelper.getExpectedAuthor(author);

            RequestGetBooks requestGetBooks = new RequestGetBooks();
            requestGetBooks.setId(String.valueOf(author.getId()));

            List<Books> booksList = RequestSteps.getBooksByAuthor(requestGetBooks);

            GetBookAssertions.assertBooksListNotNullAndNotEmpty(booksList);
            GetBookAssertions.assertBooksMatchAuthor(booksList, expectedAuthor);
        }


    @Test
    @DisplayName("Получение книг в формате XML. Позитивный тест")
    @Description("Получены книги в формате XML")
    public void testBooksByAuthorPostXML() {
        Authors author = DataHelper.getSavedAuthor();
        Authors expectedAuthor = DataHelper.getExpectedAuthor(author);

        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        requestPostBooksXML.setAuthor(author);

        Response response = RequestSteps.getBooksByAuthorXMLAndGetResponse(requestPostBooksXML);

        ResponsePostBooksXML responseAuthorsBooksXML = response.as(ResponsePostBooksXML.class);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(responseAuthorsBooksXML.getBooks());
        GetBookAssertions.assertBooksMatchAuthor(responseAuthorsBooksXML.getBooks(), expectedAuthor);
    }

    @Test
    @DisplayName("Получение книг не сохраненного автора в формате json. Негативный тест")
    @Description("Сервис возвращает http код ошибки = 400 с описанием: “Указанный автор не существует в таблице”")
    public void successGetBooksByAuthor() {
        Authors author = DataHelper.getUnsavedAuthor();

        RequestGetBooks requestGetBooks = new RequestGetBooks();
        requestGetBooks.setId(String.valueOf(author.getId()));

        Response response = RequestSteps.getBooksByAuthorAndGetResponse(requestGetBooks);

        GetBookAssertions.checkStatusCode(response, 409);

        GetBookAssertions.asserErrorMessage(response, AUTHOR_NOT_FOUND);
    }

    @Test
    @DisplayName("Получение книг несохраненного автора в формате xml. Негативный тест")
    @Description("Сервис возвращает Http код ошибки = 400 с описанием: “Указанный автор не существует в таблице”")
    public void testGetBookUnsavedAuthorXML() {
        Authors author = DataHelper.getUnsavedAuthor();

        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        requestPostBooksXML.setAuthor(author);

        Response response = RequestSteps.getBooksByAuthorXMLAndGetResponse(requestPostBooksXML);

        checkStatusCode(response, 409);

        GetBookAssertions.asserErrorMessageXML(response, AUTHOR_NOT_FOUND);
    }

    @Test
    @DisplayName("Получение книг автора с незаполненным ID автора json. Негативный тест")
    @Description("Сервис возвращает http код ошибки = 400")
    public void testGetBookWithoutId() {
        RequestGetBooks requestGetBooks = new RequestGetBooks();

        Response response = RequestSteps.getBooksByAuthorAndGetResponse(requestGetBooks);

        checkStatusCode(response, 400);

        GetBookAssertions.asserErrorMessage(response, INVALID_REQUIRED_PARAMETER);
    }

    @Test
    @DisplayName("Получение книг автора с незаполненным ID автора xml. Негативный тест")
    @Description("Сервис возвращает http код ошибки = 400")
    public void testGetBookWithoutIdXML() {
        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        Authors author = new Authors();
        requestPostBooksXML.setAuthor(author);

        Response response = RequestSteps.getBooksByAuthorXMLAndGetResponse(requestPostBooksXML);

        checkStatusCode(response, 409);

        GetBookAssertions.asserErrorMessageXML(response, INVALID_REQUIRED_PARAMETER);
    }

    @Test
    @DisplayName("Получение книг автора, у которого нет сохраненных книг json. Позитивный тест")
    @Description("Возвращается пустой список и статус 200")
    void testGetBooksByAuthorWithoutBooks() {
        Authors author = DataHelper.getSavedAuthorWithoutBooks();

        RequestGetBooks requestGetBooks = new RequestGetBooks();
        requestGetBooks.setId(String.valueOf(author.getId()));

        List<Books> booksList = RequestSteps.getBooksByAuthor(requestGetBooks);

        Response response = RequestSteps.getBooksByAuthorAndGetResponse(requestGetBooks);
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

        Response response = RequestSteps.getBooksByAuthorXMLAndGetResponse(requestPostBooksXML);
        GetBookAssertions.checkStatusCode(response, 200);

        GetBookAssertions.assertBooksListIsEmpty(booksList);
    }
}