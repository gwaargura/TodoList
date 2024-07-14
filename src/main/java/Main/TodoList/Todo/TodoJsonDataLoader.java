package Main.TodoList.Todo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class TodoJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TodoJsonDataLoader.class);

    private final JdbcClientTodoRepository todoRepository;
    private final ObjectMapper objectMapper;

    @Value("${data.file.path:/data/data.json}")
    private String dataFilePath;

    public TodoJsonDataLoader(JdbcClientTodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        if (todoRepository.count() == 0) {
            loadTodosFromFile();
        } else {
            log.info("Data has already been loaded");
        }
    }

    private void loadTodosFromFile() {
        try (InputStream input = TypeReference.class.getResourceAsStream(dataFilePath)) {
            Todos all = objectMapper.readValue(input, Todos.class);
            log.info("Reading todos from file...");
            log.info("Found {} records.", all.todos().size());
            todoRepository.saveAll(all.todos());
            log.info("Done reading todos from file.");
        } catch (Exception e) {
            throw new RuntimeException("Error reading todos from file.", e);
        }
    }
}
