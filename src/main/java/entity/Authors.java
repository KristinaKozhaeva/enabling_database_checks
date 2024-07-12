package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "author")

public class Authors {

    @XmlElement(name = "id")
    @JsonProperty("id")
    private long id;

    @XmlElement(name = "firstName")
    @JsonProperty("firstName")
    private String firstName;

    @XmlElement(name = "familyName")
    @JsonProperty("familyName")
    private String familyName;

    @XmlElement(name = "secondName")
    @JsonProperty("secondName")
    private String secondName;
}
