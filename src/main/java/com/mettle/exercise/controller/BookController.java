package com.mettle.exercise.controller;

import com.mettle.exercise.model.Book;
import com.mettle.exercise.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/book")
    public void addBooks(@RequestBody Book book) {
        bookService.saveBook(book);
    }
}
