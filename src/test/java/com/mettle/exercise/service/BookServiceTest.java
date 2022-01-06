package com.mettle.exercise.service;

import com.mettle.exercise.model.Book;
import com.mettle.exercise.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void getAllBooks(){
//        given
        when(bookRepository.findAll()).thenReturn(List.of(new Book("The Choice", "Edith Eger", 9.99),
                new Book("Rich Dad Poor Dad", "Robert Kiyosaki", 10.99)));
//        when
        List<Book> books = bookService.getBooks();
//        then
        assertEquals(books.size(), 2);
    }

    @Test
    public void findBookById(){
//        given
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(new Book("The Choice", "Edith Eger", 9.99)));
//        when
        Book book = bookService.getBookById(1L);
//        then
        assertEquals(book.getName(), "The Choice");
        assertEquals(book.getAuthor(), "Edith Eger");
    }

    @Test
    public void throwEntityNotFoundException(){
//        given
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
//        when & then
        assertThrows(EntityNotFoundException.class, () -> {
            bookService.getBookById(1L);
        });
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void saveBook(){
//        given
        Book newBook = new Book("The Choice", "Edith Eger", 9.99);
//        when
        bookService.saveBook(newBook);
//        then
        verify(bookRepository, times(1)).save(newBook);
    }
}
