package org.example.springdatajdbc.repositories;

import org.example.springdatajdbc.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Book save(Book book);

    Optional<Book> findById(Long id);

    void deleteById(Long id);
}
