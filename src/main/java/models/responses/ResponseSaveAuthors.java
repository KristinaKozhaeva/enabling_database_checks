package models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ResponseSaveAuthors {

    private long authorId;

    @JsonProperty("errorCode")
    private int errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("errorDetails")
    private String errorDetails;
}
