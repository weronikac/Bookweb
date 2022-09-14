package zti.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import zti.project.Utils.BookData;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("books")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private List<Review> reviews = new ArrayList<>();

    public Book(BookData book){
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.genre = book.getGenre();
        this.isbn = book.getIsbn();
        this.reviews = book.getReviews();
    }


}
