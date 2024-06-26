package steps.asserts;

import entity.Books;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GetBookAssertions {

    public static void assertBooksListNotNullAndNotEmpty(List<Books> books) {
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }
}