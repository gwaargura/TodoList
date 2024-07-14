package Main.TodoList.Todo;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Todo(
        @Id
        Integer id,
        @NotEmpty
        String goal,
        String createDate,
        String dueDate,
        Integer completed,
        @Version
        Integer version
) {
}

