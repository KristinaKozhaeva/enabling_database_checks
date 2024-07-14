package utils;

import configuration.RequestBuilder;
import entity.Authors;
import entity.Books;
import models.requests.RequestSaveAuthors;
import models.requests.RequestSaveBooks;
import models.responses.ResponseSaveAuthors;
import steps.requestSteps.RequestSteps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class DataHelper {

    public static Authors getSavedAuthor() {

        String firstName = randomAlphabetic(2,40);
        String familyName = randomAlphabetic(2,40);
        String secondName = randomAlphabetic(2,40);
        DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = data.format(LocalDate.of(1888, 8, 8));

        RequestSaveAuthors request = new RequestSaveAuthors(firstName, familyName, secondName, birthDate);
        ResponseSaveAuthors response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(request))
                .post()
                .as(ResponseSaveAuthors.class);

        Authors author = new Authors(response.getAuthorId(), firstName, familyName, secondName, birthDate);

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle(randomAlphabetic(2,40));
        requestSaveBooks.setAuthor(author);
        RequestSteps.saveBook(requestSaveBooks);

        return author;
    }

    public static Authors getUnsavedAuthor() {
        DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = data.format(LocalDate.of(1888, 8, 8));

        return new Authors(-1L, "Тестовый", "Автор", "Несуществующий", birthDate);
    }

    public static RequestSaveBooks getBookWithoutAuthorId() {
        Authors author = new Authors();
        author.setFirstName(randomAlphabetic(2,40));
        author.setFamilyName(randomAlphabetic(2,40));
        author.setSecondName(randomAlphabetic(2,40));
        DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = data.format(LocalDate.of(1888, 8, 8));

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Книга без id автора");
        requestSaveBooks.setAuthor(author);

        return requestSaveBooks;
    }

    public static Authors getSavedAuthorWithoutBooks() {

        String firstName = randomAlphabetic(2,40);
        String familyName = randomAlphabetic(2,40);
        String secondName = randomAlphabetic(2,40);
        DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = data.format(LocalDate.of(1888, 8, 8));

        RequestSaveAuthors request = new RequestSaveAuthors(firstName, familyName, secondName, birthDate);
        ResponseSaveAuthors response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(request))
                .post()
                .as(ResponseSaveAuthors.class);

        Authors author = new Authors(response.getAuthorId(), firstName, familyName, secondName, birthDate);
        return author;
    }

    public static Authors getSavedAuthorWithoutBooksXML() {

        String firstName = randomAlphabetic(2,40);
        String familyName = randomAlphabetic(2,40);
        String secondName = randomAlphabetic(2,40);
        DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = data.format(LocalDate.of(1888, 8, 8));

        RequestSaveAuthors request = new RequestSaveAuthors(firstName, familyName, secondName, birthDate);
        ResponseSaveAuthors response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(request))
                .post()
                .as(ResponseSaveAuthors.class);

        Authors author = new Authors(response.getAuthorId(), firstName, familyName, secondName, birthDate);
        return author;
    }

    public static Authors getExpectedAuthor(Authors actualAuthor) {
        Authors expectedAuthor = new Authors();
        expectedAuthor.setId(actualAuthor.getId());
        expectedAuthor.setFirstName(actualAuthor.getFirstName());
        expectedAuthor.setFamilyName(actualAuthor.getFamilyName());
        expectedAuthor.setSecondName(actualAuthor.getSecondName());
        expectedAuthor.setBirthDate(actualAuthor.getBirthDate());

        return expectedAuthor;
    }

    public static Books getExpectedBook(Books actualBook) {
        Books expectedBook = new Books();
        expectedBook.setId(actualBook.getId());
        expectedBook.setBookTitle(actualBook.getBookTitle());
        expectedBook.setAuthorID(actualBook.getAuthorID());
        expectedBook.setUpdated(actualBook.getUpdated());

        return expectedBook;
    }
}