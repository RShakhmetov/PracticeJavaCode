package org.example.springmvclibrary.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmvclibrary.model.Book;
import org.example.springmvclibrary.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("/getBooks")
    public ResponseEntity<Page<Book>> getBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(id, book));
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.delete(id));
    }
}
