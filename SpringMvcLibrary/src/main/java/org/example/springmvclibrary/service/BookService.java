package org.example.springmvclibrary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springmvclibrary.model.Author;
import org.example.springmvclibrary.model.Book;
import org.example.springmvclibrary.repository.AuthorRepository;
import org.example.springmvclibrary.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @Transactional
    public Book addBook(Book createBook) {
        Author author = authorRepository.save(createBook.getAuthor());
        createBook.setAuthor(author);
        return bookRepository.save(createBook);
    }
}
