package Main.TodoList.Todo;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    public static String convertDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");
        return dateTime.format(formatter);
    }

}
