package zti.project.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import zti.project.model.Book;
import zti.project.model.Review;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookData {
    @Id
    private String id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String genre;
    @NotNull
    private String isbn;
    @NotNull
    private List<Review> reviews = new ArrayList<>();

    public BookData(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
        this.isbn = book.getIsbn();
        this.reviews = book.getReviews();
    }
}

