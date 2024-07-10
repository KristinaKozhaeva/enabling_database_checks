package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Authors;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class RequestSaveBooks {

    private String bookTitle;
    private Authors author;
}
