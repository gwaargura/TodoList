package Main.TodoList.User;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {

    private final RestClient restClient;


    /*
    * You can swap out to different Client libraries with requestFactory()
    *
    * You can pause the request and interact with the data before it's sent away with requestInterceptor()
    * */
    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .requestFactory(new JdkClientHttpRequestFactory())
//                .requestInterceptor()
                .build();
    }

    public List<User> findAll(){
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {});
    }

    public User findById(Integer id){
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class);
    }
}
