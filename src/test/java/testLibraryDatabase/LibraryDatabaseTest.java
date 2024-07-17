package testLibraryDatabase;

import entity.Books;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.dataBaseSteps.DatabaseOperations;

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

        operations.insertBooks(book1, 8L);
        operations.insertBooks(book2, 88L);

        List<Books> books = operations.findAll();
        System.out.println(books);

        List<Books> firstBook = operations.findByBookTitle(book1);
        System.out.println(firstBook);

        operations.deleteBookByTitle(book1);

        List<Books> secondBook = operations.findAll();
        System.out.println(secondBook);
    }
}
