package models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.Authors;
import entity.Books;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "authors_books")
@XmlAccessorType(XmlAccessType.FIELD)

public class ResponsePostBooksXML {

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    private List<Books> books;

    @XmlElement(name = "author")
    private Authors author;

    private int errorCode;
    private String errorMessage;
    private String errorDetails;

}
