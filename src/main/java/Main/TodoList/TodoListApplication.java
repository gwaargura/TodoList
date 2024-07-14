package Main.TodoList;

import Main.TodoList.Todo.Todo;
import Main.TodoList.User.User;
import Main.TodoList.User.UserHttpClient;
import Main.TodoList.User.UserRestClient;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class TodoListApplication {

    private static final Logger logger = LoggerFactory.getLogger(TodoListApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }

    @Bean
    UserHttpClient userHttpClient(){
        RestClient restClient = RestClient.create("");
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserHttpClient.class);
    }

//    @Bean
//    CommandLineRunner runner(UserRestClient client) {
//        return args -> {
//            List<User> users = client.findAll();
//            System.out.println(users);
//
//            System.out.println(client.findById(1));
//        };
//    }
}
