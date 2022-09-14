package zti.project.Utils;

import lombok.Data;
import zti.project.model.User;

import javax.validation.constraints.NotNull;

@Data
public class ReviewData {
    @NotNull
    private String content;
}
