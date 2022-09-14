package zti.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import zti.project.Utils.UserCredentials;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String name;
    @CreatedDate
    private Instant createdAt;
    private List<Book> readBooks = new ArrayList<>();

    public User(UserCredentials newUser) {
        this.email = newUser.getEmail();
        this.password = newUser.getPassword();
        this.name = newUser.getName();
    }
}
