package models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Authors;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class RequestSaveBooks {

    private String bookTitle;
    private Authors author;
    private Date updated;
}
