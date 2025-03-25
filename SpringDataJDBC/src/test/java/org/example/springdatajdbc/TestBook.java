package org.example.springdatajdbc;

import lombok.RequiredArgsConstructor;
import org.example.springdatajdbc.model.Book;
import org.example.springdatajdbc.repositories.BookRepositoryImpl;
import org.example.springdatajdbc.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = SpringDataJdbcApplication.class)
@RequiredArgsConstructor
public class TestBook {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepositoryImpl bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book(1L, "Title", "Author", LocalDate.of(2024, 1, 1));
        bookRepository.save(book);
    }

    @Test
    void testCreateBook() {
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Book savedBook = bookService.save(book);
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Title");
    }

    @Test
    void testGetBookById() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(book));
        Book foundBook = bookService.findById(1L);
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getId()).isEqualTo(1L);
        assertThat(foundBook.getTitle()).isEqualTo("Title");
        assertThat(foundBook.getAuthor()).isEqualTo("Author");
    }

    @Test
    void testDeleteBook() {

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.delete(1L);

        Mockito.verify(bookRepository, times(1)).deleteById(1L);
    }
}
