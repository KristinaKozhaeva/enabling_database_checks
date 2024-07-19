package testLibraryDatabase;

import entity.Authors;
import entity.Books;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.asserts.LibraryDatabaseAssertions;
import steps.dataBaseSteps.DatabaseOperations;

import java.util.Date;
import java.util.List;

@Epic("Тестирование базы данных")
@Story("Операции с базой данных")
public class LibraryDatabaseTest {

    private DatabaseOperations operations = new DatabaseOperations();

    @Test
    @DisplayName("Тест для таблицы Book")
    public void databaseTest() {
        String book1 = "first book";
        String book2 = "second book";

        operations.deleteAll();

        Date currentDate = new Date();

        operations.insertBooks(book1, 8L, currentDate);
        operations.insertBooks(book2, 88L, currentDate);

        List<Books> books = operations.findAll();
        LibraryDatabaseAssertions.assertInitialBooks(books, 2);

        List<Books> firstBook = operations.findByBookTitle(book1);
        LibraryDatabaseAssertions.assertFirstBook(firstBook);

        operations.deleteBookByTitle(book1);

        List<Books> secondBook = operations.findAll();
        LibraryDatabaseAssertions.assertBooksAfterDelete(secondBook, 1);
    }
}
