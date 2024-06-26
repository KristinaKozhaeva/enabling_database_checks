package testAPIMethod;

import configuration.RequestBuilder;

import entity.Books;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import models.requests.RequestGetBooks;
import models.responses.ResponseGetBooks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.GetBookAssertions;

import java.util.List;
import static io.restassured.RestAssured.given;

@Epic("Get-запросы")
@Story("Получение книг автора")
public class GetBooksStepsTest {

    @Test
    @DisplayName("Получение книг автора")
    @Description("Книги автора успешно получены")
    public void testGetBooksByAuthor() {
        RequestGetBooks request = new RequestGetBooks();
        request.setAuthorId("8");

        ResponseGetBooks response = given()
                .spec(RequestBuilder.requestGetBookSpec())
                .body(request)
                .pathParam("authorId", request.getAuthorId())
                .when()
                .get("/authors/{authorId}/books")
                .then()
                .extract()
                .as(ResponseGetBooks.class);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(response.getBooks());
    }

    @Test
    @DisplayName("Получение книг автора в формате XML. POST-запрос")
    @Description("Книги автора в формате XML успешно получены")
    public void testBooksByAuthorPostXML() {

        RequestGetBooks request = new RequestGetBooks();
        request.setAuthorId("88");

        List<Books> booksList = given()
                .spec(RequestBuilder.requestPostBookSpecXML())
                .contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body(request)
                .pathParam("authorId", request.getAuthorId())
                .when()
                .post("/authors/{authorId}/books")
                .then()
                .extract()
                .xmlPath().getList(".", Books.class);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(booksList);
    }
}
