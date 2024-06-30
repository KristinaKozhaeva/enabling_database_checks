package testAPIMethod;

import configuration.RequestBuilder;
import entity.Authors;
import entity.Books;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.requests.RequestGetBooks;

import models.requests.RequestPostBooksXML;
import models.responses.ResponseGetBooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.GetBookAssertions;

import java.util.List;

import static configuration.RequestBuilder.requestGetBookSpec;
import static io.restassured.RestAssured.given;

@Epic("Запросы на получение книг")
@Story("Получение книг автора")
public class GetBooksStepsTest {

    @Test
    @DisplayName("Получение книг автора")
    @Description("Книги автора успешно получены")

    public void testGetBooksByAuthor() {
        RequestGetBooks requestGetBooks = new RequestGetBooks();
        requestGetBooks.setAuthorId("12");

        ResponseGetBooks responseGetBooks = given()
                .spec(requestGetBookSpec(requestGetBooks))
                .get()
                .then()
                .extract()
                .as(ResponseGetBooks.class);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(responseGetBooks.getBooks());
    }

    @Test
    @DisplayName("Получение книг в формате XML")
    @Description("Получены книг в формате XML")
    public void testBooksByAuthorPostXML() {

        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        Authors author = new Authors();
        author.setId(66);
        requestPostBooksXML.setAuthor(author);

        List<Books> booksList = given()
                .spec(RequestBuilder.requestPostBookSpecXML(requestPostBooksXML))
                .when()
                .post()
                .then()
                .extract()
                .xmlPath().getList(".", Books.class);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(booksList);
    }

}
