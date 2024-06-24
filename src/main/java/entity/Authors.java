package entity;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class Authors {

    @XmlElement(name = "id")
    private long id;

    @XmlElement(name = "firstName")
    private String firstName;

    @XmlElement(name = "familyName")
    private String familyName;

    @XmlElement(name = "secondName")
    private String secondName;

}
