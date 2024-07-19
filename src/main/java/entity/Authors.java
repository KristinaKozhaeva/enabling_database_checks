package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "author")
@Entity
@Table(name = "author")

public class Authors {

    @XmlElement(name = "id")
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @XmlElement(name = "firstName")
    @JsonProperty("firstName")
    @Column(name = "first_name")
    private String firstName;

    @XmlElement(name = "familyName")
    @JsonProperty("familyName")
    @Column(name = "family_name")
    private String familyName;

    @XmlElement(name = "secondName")
    @JsonProperty("secondName")
    @Column(name = "second_name")
    private String secondName;

    @XmlElement(name = "birth_date")
    @JsonProperty("birthDate")
    @Column(name = "birth_date")
    private String birthDate;
}
