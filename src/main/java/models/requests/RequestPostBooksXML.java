package models.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Authors;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class RequestPostBooksXML {

    @XmlElement(name = "author")
    @JsonProperty("author")
    private Authors author;

    @XmlElement(name = "author_id")
    public long getAuthorId() {
        return author.getId();
    }
}
