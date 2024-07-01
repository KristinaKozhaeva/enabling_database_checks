package testAPIMethod;

import entity.Authors;
import entity.Books;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.requests.RequestGetBooks;

import models.requests.RequestPostBooksXML;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.GetBookAssertions;
import steps.requestSteps.RequestSteps;

import java.util.List;

@Epic("Запросы на получение книг")
@Story("Получение книг автора")
public class GetBooksStepsTest {

    @Test
    @DisplayName("Получение книг автора")
    @Description("Книги автора успешно получены")
    public void testGetBooksByAuthor() {
        RequestGetBooks requestGetBooks = new RequestGetBooks();
        requestGetBooks.setAuthorId("12");

        List<Books> booksList = RequestSteps.getBooksByAuthor(requestGetBooks);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(booksList);
    }

    @Test
    @DisplayName("Получение книг в формате XML")
    @Description("Получены книги в формате XML")
    public void testBooksByAuthorPostXML() {
        RequestPostBooksXML requestPostBooksXML = new RequestPostBooksXML();
        Authors author = new Authors();
        author.setId(66);
        requestPostBooksXML.setAuthor(author);

        List<Books> booksList = RequestSteps.getBooksByAuthorXML(requestPostBooksXML);

        GetBookAssertions.assertBooksListNotNullAndNotEmpty(booksList);
    }
}


