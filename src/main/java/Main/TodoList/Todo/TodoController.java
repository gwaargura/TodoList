package Main.TodoList.Todo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @GetMapping("/id={id}")
    Optional<Todo> getById(@PathVariable int id) {
        Optional<Todo> op = todoRepository.findById(id);

        if (op.isPresent()) {
            return op;
        }
        throw new TodoNotFoundException();
    }

    @ResponseStatus(HttpStatus.CREATED) //STATUS = 201
    @PostMapping("/todos")
    void create(@Valid @RequestBody Todo todo) {
        todoRepository.save(todo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) //STATUS = 204
    @PutMapping("/todos")
    void update(@Valid @RequestBody Todo todo) {
        todoRepository.save(todo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) //STATUS = 204
    @DeleteMapping("/delete={id}")
    void delete(@PathVariable int id) {
        todoRepository.delete(todoRepository.findById(id).get());
    }

    @GetMapping("/completed={completed}")
    List<Todo> findByGoal(@PathVariable int completed){
        return todoRepository.findByCompleted(completed);
    }
}
