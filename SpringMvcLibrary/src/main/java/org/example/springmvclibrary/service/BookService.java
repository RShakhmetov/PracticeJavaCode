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
        if (bookRepository.existsByTitle(createBook.getTitle())) {
            throw new RuntimeException("Book already exists");
        }
        Author author = authorRepository.save(createBook.getAuthor());
        createBook.setAuthor(author);
        return bookRepository.save(createBook);
    }

    @Transactional
    public Book updateBook(Long id, Book updateBook) {
        if (bookRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        Book book = bookRepository.findById(id).get();
        book.setTitle(updateBook.getTitle());
        book.getAuthor().setFirstName(updateBook.getAuthor().getFirstName());
        book.getAuthor().setLastName(updateBook.getAuthor().getLastName());
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        if (bookRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        return bookRepository.findById(id).get();
    }

    public Book delete(Long id) {
        if (bookRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
        return book;
    }
}
