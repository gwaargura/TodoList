package Main.TodoList.Todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
*
* NOT COMPLETED!
* SOMETHING IS STILL WRONG
*
* */

@WebMvcTest(controllers = TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    TodoRepository repo;

    private final static List<Todo> todos = new ArrayList<>();


    @BeforeEach
    void setUp() {
        todos.add(
                new Todo(
                        12,
                        "Hello",
                        "Now",
                        "Later",
                        1

                )
        );
    }

    @Test
    void getAll() throws Exception {
        when(repo.findAll()).thenReturn(todos);
        mvc.perform(MockMvcRequestBuilders.get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(todos.size())
                ));
    }

    @Test
    void shouldFindOneTodo() throws Exception {
        Todo todo = todos.get(todos.size() - 1);
        when(repo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(todo));
        mvc.perform(get("/api/todos/12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(todo.id())))
                .andExpect(jsonPath("$.goal", is(todo.goal())))
                .andExpect(jsonPath("$.createDate", is(todo.createDate())))
                .andExpect(jsonPath("$.dueDate", is(todo.dueDate())))
                .andExpect(jsonPath("$.completed", is(todo.completed())));
    }

    @Test
    void shouldReturnNotFoundWithInvalidId() throws Exception {
        mvc.perform(get("/api/todos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewTodo() throws Exception {
        var todo = new Todo(
                12,
                "Hello",
                "Now",
                "Later",
                1
        );
        mvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(todo))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateTodo() throws Exception {
        var todo = new Todo(
                12,
                "Hello",
                "Now",
                "Later",
                1
        );
        mvc.perform(put("/api/todos/12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(todo))
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTodo() throws Exception {
        mvc.perform(delete("/api/todos/12"))
                .andExpect(status().isNoContent());
    }
}