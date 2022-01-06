package com.mettle.exercise;

import com.mettle.exercise.model.Book;
import com.mettle.exercise.model.User;
import com.mettle.exercise.repository.BookRepository;
import com.mettle.exercise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringBootApplication
@AllArgsConstructor
public class ExerciseApplication {

    private BookRepository bookRepository;
    private UserRepository userRepository;


    //	Populating user and books with dummy data
    @PostConstruct
    private void populateUsersAndBooks() {
        List books = List.of(new Book("The Choice", "Edith Eger", 9.99),
                new Book("Rich Dad Poor Dad", "Robert Kiyosaki", 10.99));
        bookRepository.saveAll(books);

        List users = List.of(new User(1, "admin", "password", "READ_AUTHORITY,WRITE_AUTHORITY,DELETE_AUTHORITY,ACCESS_ALL_AUTHORITY"),
                new User(2, "user", "password", "READ_AUTHORITY"));
        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

}
