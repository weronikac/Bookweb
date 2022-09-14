package zti.project.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import zti.project.model.Book;
import zti.project.model.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String name;
    @CreatedDate
    private Instant createdAt;
    private List<Book> readBooks = new ArrayList<>();

    public UserData(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.createdAt = user.getCreatedAt();
        this.readBooks = user.getReadBooks();
    }
}
