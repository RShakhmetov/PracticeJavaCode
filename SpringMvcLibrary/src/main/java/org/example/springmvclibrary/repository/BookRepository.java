package org.example.springmvclibrary.repository;

import org.example.springmvclibrary.model.Author;
import org.example.springmvclibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);

    Book findByAuthor(Author author);
}
