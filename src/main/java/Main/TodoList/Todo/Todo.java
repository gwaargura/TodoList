package Main.TodoList.Todo;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

public record Todo(
        @Id
        Integer id,
        @NotEmpty
        String goal,
        String createDate,
        String dueDate,
        Integer completed
) {
}

