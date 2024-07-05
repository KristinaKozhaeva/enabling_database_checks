package utils;

import configuration.RequestBuilder;
import entity.Authors;
import models.requests.RequestSaveAuthors;
import models.requests.RequestSaveBooks;
import models.responses.ResponseSaveAuthors;
import steps.requestSteps.RequestSteps;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class DataHelper {

    public static Authors getSavedAuthor() {

        String firstName = randomAlphabetic(10);
        String familyName = randomAlphabetic(10);
        String secondName = randomAlphabetic(10);

        RequestSaveAuthors request = new RequestSaveAuthors(firstName, familyName, secondName);
        ResponseSaveAuthors response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(request))
                .post()
                .as(ResponseSaveAuthors.class);

        Authors author = new Authors(response.getAuthorId(), firstName, familyName, secondName);

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Тестирование");
        requestSaveBooks.setAuthor(author);
        RequestSteps.saveBook(requestSaveBooks);

        return author;
    }

    public static Authors getUnsavedAuthor() {
        return new Authors(-1L, "Тестовый", "Автор", "Несуществующий");
    }

    public static RequestSaveBooks getBookWithoutAuthorId() {
        Authors author = new Authors();
        author.setFirstName(randomAlphabetic(10));
        author.setFamilyName(randomAlphabetic(10));
        author.setSecondName(randomAlphabetic(10));

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Книга без id автора");
        requestSaveBooks.setAuthor(author);

        return requestSaveBooks;
    }

    public static Authors getSavedAuthorWithoutBooks() {

        String firstName = randomAlphabetic(10);
        String familyName = randomAlphabetic(10);
        String secondName = randomAlphabetic(10);

        RequestSaveAuthors request = new RequestSaveAuthors(firstName, familyName, secondName);
        ResponseSaveAuthors response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(request))
                .post()
                .as(ResponseSaveAuthors.class);

        Authors author = new Authors(response.getAuthorId(), firstName, familyName, secondName);
        return author;
    }

    public static Authors getSavedAuthorWithoutBooksXML() {
        String firstName = randomAlphabetic(10);
        String familyName = randomAlphabetic(10);
        String secondName = randomAlphabetic(10);

        RequestSaveAuthors request = new RequestSaveAuthors(firstName, familyName, secondName);
        ResponseSaveAuthors response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(request))
                .post()
                .as(ResponseSaveAuthors.class);

        Authors author = new Authors(response.getAuthorId(), firstName, familyName, secondName);
        return author;
    }
}




