package Main.TodoList.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTodoRepositoryTest {

    InMemoryTodoRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryTodoRepository();
        repo.create(
                new Todo(
                        1,
                        "Wake up",
                        "Monday 7 pm",
                        "Tuesday 7 am",
                        1
                )
        );
        repo.create(
                new Todo(
                        1,
                        "Eat breakfast",
                        "Monday 7 pm",
                        "Tuesday 8 am",
                        0
                )
        );
    }

    @Test
    void findAll() {
        List<Todo> todos = repo.findAll();
        assertEquals(2, todos.size(), "Should have 2 todos");
    }

    @Test
    void findById() {
        var todo = repo.findById(1).get();
        assertEquals("Wake up", todo.goal());
        assertEquals("Monday 7 pm", todo.createDate());
    }

    @Test
    void shouldNotFindById() {
        TodoNotFoundException ex = assertThrows(
                TodoNotFoundException.class,
                () -> repo.findById(-1).get()
        );

        assertEquals("Todo not found", ex.getMessage());
    }

    @Test
    void create() {
        repo.create(
                new Todo(
                        1,
                        "Go for a walk",
                        "Monday 7 pm",
                        "Tuesday 9 am",
                        0
                )
        );
        List<Todo> todos = repo.findAll();
        assertEquals(3, todos.size(), "Should have 3 todos");
    }

    @Test
    void update() {
        repo.update(
                new Todo(
                        1,
                        "Take a walk",
                        "Monday 7 pm",
                        "Tuesday 10 am",
                        0
                ), 1
        );
    }

    @Test
    void delete() {
        repo.delete(1);
        List<Todo> todos = repo.findAll();
        assertEquals(1, todos.size(), "Should have 1 todo");
    }

    @Test
    void count() {
    }

    @Test
    void saveAll() {
    }
}