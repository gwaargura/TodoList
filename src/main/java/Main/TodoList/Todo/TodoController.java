package Main.TodoList.Todo;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class TodoController {

    private final TodoRepository todoRepository;
    private int todoId;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        todoId = (int)todoRepository.count();
    }

//    @GetMapping("/todos")
//    List<Todo> getAll() {
//        return todoRepository.findAll();
//    }

    @GetMapping("/todos")
    public String index(Model model){
        List<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos", todos);
        return "index";
    }


    // Endpoint to handle paginated TODO list
    @GetMapping("/todos/page={page}")
    public String index(Model model, @PathVariable Integer page) {
        int pageSize = 5; // Number of items per page
        int start = (page - 1) * pageSize + 1;
        int end = start + pageSize - 1;
        long pages = todoRepository.count();
        if(pages%5 != 0){
            pages = pages/5 + 1;
        }
        else{
            pages = pages/5;
        }
        List<Todo> todos = todoRepository.findByPaging(start, end);

        model.addAttribute("todos", todos);
        model.addAttribute("pages", pages);
        model.addAttribute("totalTodos", todoId);
        return "index";
    }

    @GetMapping("?id={id}")
    Optional<Todo> getById(@PathVariable int id) {
        Optional<Todo> op = todoRepository.findById(id);

        if (op.isPresent()) {
            return op;
        }
        throw new TodoNotFoundException();
    }

    @ResponseStatus(HttpStatus.CREATED) //STATUS = 201
    @PostMapping("/todos")
    public void create(@RequestBody Todo todo) {
        todoRepository.insertTodo(todo.goal(), todo.createDate(), todo.dueDate(), todo.completed());
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
