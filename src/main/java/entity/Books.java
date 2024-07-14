package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books")

public class Books {

    @XmlElement(name = "id")
    @JsonProperty("id")
    private long id;

    @XmlElement(name = "book_title")
    @JsonProperty("bookTitle")
    private String bookTitle;

    @XmlElement(name = "author")
    @JsonProperty("author")
    private Authors authorID;

    @XmlElement(name = "updated")
    @JsonProperty("updated")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updated;
}
