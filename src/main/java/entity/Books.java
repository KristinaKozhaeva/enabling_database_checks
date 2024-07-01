package entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class Books {

    @XmlElement(name = "id")
    @JsonProperty("id")
    private long id;

    @XmlElement(name = "bookTitle")
    @JsonProperty("bookTitle")
    private String bookTitle;

    @XmlElement(name = "authorID")
    @JsonProperty("authorID")
    private Authors authorID;

}
