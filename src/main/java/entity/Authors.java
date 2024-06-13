package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Authors {

    private long Id;
    private String firstName;
    private String familyName;
    private String secondName;
}
