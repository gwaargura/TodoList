package Main.TodoList.Todo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TodoRepository extends ListCrudRepository<Todo, Integer> {
    List<Todo> findAllByDueDate(String dueDate);

    List<Todo> findByGoal(String dueDate);

//    You can use your own custom Sql command
//    @Query("SELECT * FROM TABLE ( Todo ) WHERE completed = :completed")
    List<Todo> findByCompleted(Integer completed);
}
