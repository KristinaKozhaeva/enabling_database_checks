package steps.asserts;

import entity.Authors;
import entity.Books;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class LibraryDatabaseAssertions {
    public static void assertInitialBooks(List<Books> books, int expectedSize) {
        System.out.println(books);
        Assertions.assertEquals(expectedSize, books.size());
    }

    public static void assertFirstBook(List<Books> firstBook) {
        System.out.println(firstBook);
        Assertions.assertFalse(firstBook.isEmpty());
    }

    public static void assertBooksAfterDelete(List<Books> books, int expectedSize) {
        System.out.println(books);
        Assertions.assertEquals(expectedSize, books.size());
    }

    public static void assertAuthorFound(Authors author, String expectedFirstName, String expectedFamilyName) {
        Assertions.assertNotNull(author);
        Assertions.assertEquals(expectedFirstName, author.getFirstName());
        Assertions.assertEquals(expectedFamilyName, author.getFamilyName());
    }
}
