package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Authors;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestSaveBooks {

    private String bookTitle;
    private Authors author;

}
