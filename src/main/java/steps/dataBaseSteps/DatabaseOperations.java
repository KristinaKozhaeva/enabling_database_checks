package steps.dataBaseSteps;

import configuration.LibraryDatabaseConfiguration;
import entity.Authors;
import entity.Books;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void insertAuthor(String firstName, String familyName, String secondName, String birthDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = sdf.parse(birthDate);
        java.sql.Date sqlBirthDate = new java.sql.Date(parsedDate.getTime());

        Authors author = new Authors();
        author.setFirstName(firstName);
        author.setFamilyName(familyName);
        author.setSecondName(secondName);
        author.setBirthDate(birthDate);

        Transaction tr = session.beginTransaction();
        session.save(author);
        session.createNativeQuery("""
            INSERT INTO author (first_name, family_name, second_name, birth_date)
            VALUES (:firstName, :familyName, :secondName, :birthDate)
            """)
                .setParameter("firstName", firstName)
                .setParameter("familyName", familyName)
                .setParameter("secondName", secondName)
                .setParameter("birthDate", sqlBirthDate)
                .executeUpdate();
        tr.commit();
    }

    public void insertAuthor(String firstName, String familyName, String secondName, Date birthDate) {
        final String hql = """
                INSERT INTO author
                (first_name, family_name, second_name, birth_date)
                VALUES(:firstName, :familyName, :secondName, :birthDate)
                """;
        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql)
                .setParameter("firstName", firstName)
                .setParameter("familyName", familyName)
                .setParameter("secondName", secondName)
                .setParameter("birthDate", birthDate)
                .executeUpdate();
        tr.commit();
    }

    public Authors findAuthor(String firstName, String familyName, String secondName, Date birthDate) {
        final String hql = """
            FROM Authors
            WHERE firstName = :firstName
            AND familyName = :familyName
            AND secondName = :secondName
            AND birthDate = :birthDate
        """;

        return session.createQuery(hql, Authors.class)
                .setParameter("firstName", firstName)
                .setParameter("familyName", familyName)
                .setParameter("secondName", secondName)
                .setParameter("birthDate", birthDate)
                .uniqueResult();
    }
}
