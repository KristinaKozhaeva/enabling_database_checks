package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class RequestSaveAuthors {

    private String firstName;
    private String familyName;
    private String secondName;

}
