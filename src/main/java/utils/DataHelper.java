package utils;

import configuration.RequestBuilder;
import entity.Authors;
import io.restassured.response.Response;
import models.requests.RequestSaveAuthors;
import models.requests.RequestSaveBooks;
import models.responses.ResponseSaveAuthors;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class DataHelper {

    public static Authors getSavedAuthor() {

        String firstName = randomAlphabetic(2, 100);
        String familyName = randomAlphabetic(2, 100);
        String secondName = randomAlphabetic(2, 100);
        Response response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(new RequestSaveAuthors(firstName, familyName, secondName)))
                .post();

        ResponseSaveAuthors savedAuthor = response.as(ResponseSaveAuthors.class);

        return new Authors(savedAuthor.getAuthorId(), firstName, familyName, secondName);
    }

    public static Authors getUnsavedAuthor() {
        return new Authors(-1L, "Тестовый", "Автор", "несуществующий");
    }

    public static RequestSaveBooks getBookWithoutAuthorId() {
        Authors author = new Authors();
        author.setFirstName("Это");
        author.setFamilyName("Фамилия");
        author.setSecondName("Отчество");

        RequestSaveBooks requestSaveBooks = new RequestSaveBooks();
        requestSaveBooks.setBookTitle("Книга без id автора");
        requestSaveBooks.setAuthor(author);

        return requestSaveBooks;
    }

    public static Authors getSavedAuthorWithoutBooks() {

        String firstName = "Автор";
        String familyName = "автор";
        String secondName = "тестовый";
        Response response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(new RequestSaveAuthors(firstName, familyName, secondName)))
                .post();

        ResponseSaveAuthors savedAuthor = response.as(ResponseSaveAuthors.class);

        return new Authors(savedAuthor.getAuthorId(), firstName, familyName, secondName);
    }

    public static Authors getSavedAuthorWithoutBooksXML() {
        String firstName = "bfdbdf";
        String familyName = "bdfb";
        String secondName = "bdbfvvv";

        Response response = given()
                .spec(RequestBuilder.requestSaveAuthorSpec(new RequestSaveAuthors(firstName, familyName, secondName)))
                .post();

        ResponseSaveAuthors savedAuthor = response.as(ResponseSaveAuthors.class);

        return new Authors(savedAuthor.getAuthorId(), firstName, familyName, secondName);
    }

}




