package Main.TodoList.Todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/*
* This is a representation of writing CRUD methods from scratch using the Spring JdbcClient.
* This class has been tested and it worked normally.
* This class will not be used in this project after adding the Spring Data dependency.
* Current equivalent to this class is TodoRepository
*
* */

@Repository
public class JdbcClientTodoRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientTodoRepository.class);
    private final JdbcClient client;

    public JdbcClientTodoRepository(JdbcClient client) {
        this.client = client;
    }

    public List<Todo> getTodos() {
        return client
                .sql("Select * from Todo")
                .query(Todo.class)
                .list();
    }

    public Optional<Todo> getTodo(Integer id) {
    return client
            .sql("Select * from Todo Where id = :id")
            .param((String)"id", id)
            .query(Todo.class)
            .optional();
    }

    public void saveAll(List<Todo> todos) {
        todos.stream().forEach(this::create);
    }

    public void create(Todo todo) {
        var updated = client
                .sql("INSERT INTO Todo (id, goal, create_date, due_date, completed) VALUES (?, ?, ?, ?, ?)")
                .param(todo.id())
                .param(todo.goal())
                .param(todo.createDate())
                .param(todo.dueDate())
                .param(todo.completed())
                .update();
    }

    public void update (Todo todo) {
        var updated = client
                .sql("Update Todo set goal = ?, create_date = ?, due_date = ?, completed = ? where id = ?")
                .param(todo.goal())
                .param(todo.createDate())
                .param(todo.dueDate())
                .param(todo.completed())
                .param(todo.id())
                .update();
    }

    public void delete (Integer id) {
        var updated = client
                .sql("Delete from Todo where id = ?")
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to delete Todo with id " + id);
    }

    public int count(){
        return client
                .sql("Select * from Todo")
                .query()
                .listOfRows()
                .size();
    }
}
