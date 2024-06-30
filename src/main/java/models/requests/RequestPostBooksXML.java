package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Authors;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class RequestPostBooksXML {

    @JsonProperty("author")
    private Authors author;

    @XmlElement(name = "author_id")
    public long getAuthorId() {
        return author.getId();
    }
}
