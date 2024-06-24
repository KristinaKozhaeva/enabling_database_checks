package models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.Authors;
import entity.Books;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ResponseGetBooks {

    private List<Books> books;

    private int errorCode;
    private String errorMessage;
    private String errorDetails;

}


