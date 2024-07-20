package Main.TodoList.Todo;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InMemoryTodoRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryTodoRepository.class);
    private final List<Todo> todos = new ArrayList<>();

    public List<Todo> findAll(){
        return todos;
    }

    public Optional<Todo> findById(Integer id){
        return Optional.ofNullable(todos.stream()
                .filter(todo -> Objects.equals(todo.id(), id))
                .findFirst()
                .orElseThrow(TodoNotFoundException::new));
    }

    public void create(Todo todo) {
        Todo newTodo = new Todo(todo.id(),
                todo.goal(),
                todo.createDate(),
                todo.dueDate(),
                todo.completed(),
                todo.version());

        todos.add(newTodo);
    }

    public void update(Todo newTodo, Integer id) {
        Optional<Todo> existingTodo = findById(id);
        if(existingTodo.isPresent()) {
            var r = existingTodo.get();
            log.info("Updating Existing Todo: " + existingTodo.get());
            todos.set(todos.indexOf(r),newTodo);
        }
    }

    public void delete(Integer id) {
        log.info("Deleting Todo: " + id);
        for(Todo todo : todos) {
            if(todo.id().equals(id)) {
                todos.remove(todo);
                return;
            }
        }
    }

    public int count() {
        return todos.size();
    }

    public void saveAll(List<Todo> todos) {
        todos.stream().forEach(this::create);
    }

    @PostConstruct
    private void init() {
        todos.add(new Todo(1,
                "Monday Morning Todo",
                "Now1",
                "Later1",
                1,
                0));

        todos.add(new Todo(2,
                "Monday Night Todo",
                "Later later",
                "Later later later later",
                1,
                0));
    }

}
