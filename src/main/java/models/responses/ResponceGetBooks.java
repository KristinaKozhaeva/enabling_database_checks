package models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.Authors;
import entity.Books;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponceGetBooks {

    private List<Books> books;
    private Authors author;
    private ErrorResponse error;

}


