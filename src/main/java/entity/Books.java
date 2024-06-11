package entity;

import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Books {

    private long bookId;
    private String bookTitle;
    private long authorId;
}
