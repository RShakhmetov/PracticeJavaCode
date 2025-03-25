package org.example.springdatajdbc.service;

import lombok.RequiredArgsConstructor;
import org.example.springdatajdbc.model.Book;
import org.example.springdatajdbc.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        if (bookRepository.findById(book.getId()).isPresent()) {
            throw new RuntimeException("Book already exists!");
        }
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        Book current = bookRepository.findById(id).orElse(null);
        if (current == null) {
            throw new RuntimeException("Book does not exists!");
        }
        current.setTitle(book.getTitle());
        current.setAuthor(book.getAuthor());
        current.setPublicationYear(book.getPublicationYear());
        return bookRepository.save(current);
    }

    public Book delete(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new RuntimeException("Book does not exists!");
        }
        bookRepository.deleteById(id);
        return book;
    }
}
