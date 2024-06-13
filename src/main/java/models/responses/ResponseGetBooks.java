package models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.Authors;
import entity.Books;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetBooks {

    private List<Books> books;
    private ErrorResponse error;

}


