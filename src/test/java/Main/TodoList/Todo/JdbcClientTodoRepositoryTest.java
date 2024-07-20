package Main.TodoList.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcClientTodoRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcClientTodoRepositoryTest {

    @Autowired
    JdbcClientTodoRepository repo;

    @BeforeEach
    void setUp() {
        repo.create(
                new Todo(
                        12,
                        "Wake up",
                        "Monday 7 pm",
                        "Tuesday 7 am",
                        1,
                        0
                )
        );
        repo.create(
                new Todo(
                        13,
                        "Eat breakfast",
                        "Monday 7 pm",
                        "Tuesday 8 am",
                        0,
                        0
                )
        );
    }


    @Test
    void findAll() {
        List<Todo> todos = repo.getTodos();
        assertEquals(13, todos.size(), "Should have 13 todos");
    }

    @Test
    void findById() {
        var todo = repo.getTodo(12).get();
        assertEquals("Wake up", todo.goal());
        assertEquals("Monday 7 pm", todo.createDate());
    }

//    @Test
//    void shouldNotFindById() {
//        TodoNotFoundException ex = assertThrows(
//                TodoNotFoundException.class,
//                () -> repo.getTodo(-1).get()
//        );
//
//        assertEquals("Todo not found", ex.getMessage());
//    }

    @Test
    void create() {
        repo.create(
                new Todo(
                        14,
                        "Go for a walk",
                        "Monday 7 pm",
                        "Tuesday 9 am",
                        0,
                        0
                )
        );
        List<Todo> todos = repo.getTodos();
        assertEquals(14, todos.size(), "Should have 14 todos");
    }

    @Test
    void update() {
        repo.update(
                new Todo(
                        13,
                        "Take a walk",
                        "Monday 7 pm",
                        "Tuesday 10 am",
                        0,
                        1
                )
        );
    }

    @Test
    void delete() {
        repo.delete(13);
        List<Todo> todos = repo.getTodos();
        assertEquals(12, todos.size(), "Should have 12 todo");
    }

    @Test
    void count() {
    }
}