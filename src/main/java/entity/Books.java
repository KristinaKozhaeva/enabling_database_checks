package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Books {

    private long id;
    private String bookTitle;
    private Authors author;

}
