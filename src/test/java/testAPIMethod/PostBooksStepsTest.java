package testAPIMethod;

import io.qameta.allure.Description;

import models.requests.RequestSaveBooks;
import entity.Authors;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.responses.ResponseSaveBooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.SaveBooksAssertions;

import static steps.requestSteps.RequestSteps.saveBook;

@Epic("Запросы на сохранение книг")
@Story("Сохранение книг автора")
public class PostBooksStepsTest {

    @Test
    @DisplayName("Сохранение новой книги")
    @Description("Новая книга автора успешно сохранена")
    public void saveNewBookTest() {
        Authors author = new Authors();
        author.setId(88);

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Преступление и наказание");
        requestSaveBooks.setAuthor(author);

        ResponseSaveBooks responseSaveBooks = saveBook(requestSaveBooks);

        SaveBooksAssertions.assertResponseSaveBooks(responseSaveBooks);
    }

}

