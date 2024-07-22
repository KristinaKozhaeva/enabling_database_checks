package steps.asserts;

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

    public static void assertBooksMatchAuthor(List<Books> dbBooks, Books expectedBook) {
        Assertions.assertNotNull(dbBooks);
        Assertions.assertFalse(dbBooks.isEmpty());

        Books dbBook = dbBooks.get(0);

        Assertions.assertEquals(expectedBook.getBookTitle(), dbBook.getBookTitle());
        Assertions.assertEquals(expectedBook.getAuthorID().getId(), dbBook.getAuthorID().getId());
        Assertions.assertEquals(expectedBook.getUpdated(), dbBook.getUpdated());
    }

    public static void assertBookSaved(List<Books> dbBooks, String expectedTitle) {
        Assertions.assertNotNull(dbBooks);
        Assertions.assertFalse(dbBooks.isEmpty());

        Books savedBook = dbBooks.get(0);

        Assertions.assertEquals(expectedTitle, savedBook.getBookTitle());
    }

    public static void assertBooksListIsEmpty(List<Books> books) {
        Assertions.assertNotNull(books);
        Assertions.assertTrue(books.isEmpty());
    }
}
