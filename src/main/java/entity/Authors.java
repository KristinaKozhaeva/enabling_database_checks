package entity;

import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Authors {

    private long authorId;
    private String firstName;
    private String familyName;
    private String secondName;
}
