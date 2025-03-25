package org.example.springdatajdbc.repositories;

import lombok.RequiredArgsConstructor;
import org.example.springdatajdbc.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> findAll() {
        String sql = "select * from books";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPublicationYear(rs.getDate("publicationYear").toLocalDate());
            return book;
        });
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            String sql = "insert into books (title, author, publicationYear) values (?, ?, ?) RETURNING id";
            Long id = jdbcTemplate.queryForObject(sql, new Object[]{book.getTitle(), book.getAuthor(), java.sql.Date.valueOf(book.getPublicationYear())}, Long.class);
            book.setId(id);
        } else {
            String sql = "update books set title = ?, author = ?, publicationYear = ? where id = ?";
            jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), java.sql.Date.valueOf(book.getPublicationYear()), book.getId());
        }
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql = "select * from books where id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublicationYear(rs.getDate("publicationYear").toLocalDate());
                return Optional.of(book);
            }
            return Optional.empty();
        });
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from books where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
