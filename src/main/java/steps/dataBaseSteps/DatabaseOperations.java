package steps.dataBaseSteps;

import configuration.LibraryDatabaseConfiguration;
import entity.Authors;
import entity.Books;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class DatabaseOperations {
    private Session session = LibraryDatabaseConfiguration.getSession();

    public void deleteAll() {
        final String hql = """
        DELETE FROM Books
        """;
        Transaction tr = session.beginTransaction();
        session.createQuery(hql).executeUpdate();
        tr.commit();
    }

    public void insertBooks(String bookTitle, long authorID, Date updated) {
        Transaction tr = session.beginTransaction();
        Authors author = session.get(Authors.class, authorID);
        Books book = new Books();
        book.setBookTitle(bookTitle);
        book.setAuthorID(author);
        book.setUpdated(updated);
        session.save(book);
        tr.commit();
    }

    public List<Books> findAll() {
        final String hql = """
        FROM Books
        """;
        return session.createQuery(hql, Books.class).getResultList();
    }

    public List<Books> findByBookTitle(String bookTitle) {
        final String hql = """
        FROM Books
        WHERE bookTitle = :title
        """;
        return session.createQuery(hql, Books.class)
                .setParameter("title", bookTitle)
                .getResultList();
    }

    public void deleteBookByTitle(String bookTitle) {
        final String hql = """
        DELETE FROM Books
        WHERE bookTitle = :title
        """;
        Transaction tr = session.beginTransaction();
        session.createQuery(hql)
                .setParameter("title", bookTitle)
                .executeUpdate();
        tr.commit();
    }
}