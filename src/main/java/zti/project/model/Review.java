package zti.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import zti.project.Utils.ReviewData;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private String id;

    private String user;
    private String content;
    @CreatedDate
    private Instant createdAt;

    public Review(ReviewData review) {
        this.content = review.getContent();
    }
}
