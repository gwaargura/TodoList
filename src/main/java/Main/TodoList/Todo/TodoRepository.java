package Main.TodoList.Todo;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends ListCrudRepository<Todo, Integer> {
    List<Todo> findAllByDueDate(String dueDate);

    @Modifying
    @Transactional
    @Query("INSERT INTO (Todo) (goal, create_date, due_date, completed) VALUES (:goal, :createDate, :dueDate, :completed)")
    void insertTodo(@Param("goal") String goal,
                    @Param("createDate") String createDate,
                    @Param("dueDate") String dueDate,
                    @Param("completed") int completed);

//    You can use your own custom Sql command
//    @Query("SELECT * FROM TABLE ( Todo ) WHERE completed = :completed")
    List<Todo> findByCompleted(Integer completed);

    @Query("SELECT * FROM Todo WHERE id >= :start AND id <= :end")
    List<Todo> findByPaging(int start, int end);
}
