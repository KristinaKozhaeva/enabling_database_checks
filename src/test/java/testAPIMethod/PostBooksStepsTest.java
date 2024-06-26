package testAPIMethod;

import configuration.RequestBuilder;
import io.qameta.allure.Description;

import models.requests.RequestSaveBooks;
import entity.Authors;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.responses.ResponseSaveBooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.SaveBooksAssertions;

import static io.restassured.RestAssured.given;

@Epic("Post-запросы")
@Story("Сохранение книг автора")
public class PostBooksStepsTest {

    @Test
    @DisplayName("Сохранение новой книги")
    @Description("Новая книга автора успешно сохранена")
    public void testSaveBook() {
        Authors author = new Authors();
        author.setId(88);

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Преступление и наказание");
        requestSaveBooks.setAuthor(author);

        ResponseSaveBooks responseSaveBooks = given()
                .spec(RequestBuilder.requestSaveBookSpec())
                .body(requestSaveBooks)
                .when()
                .post("/save")
                .then()
                .extract().as(ResponseSaveBooks.class);

        SaveBooksAssertions.assertResponseSaveBooks(responseSaveBooks);
    }

}