package com.mettle.exercise.service;

import com.mettle.exercise.model.Book;
import com.mettle.exercise.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookById(Long id) throws EntityNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book Not Found: " + id));
    }
}