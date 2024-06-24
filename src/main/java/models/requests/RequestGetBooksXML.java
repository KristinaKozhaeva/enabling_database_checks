package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Authors;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class RequestGetBooksXML {

    private Authors author;
}
