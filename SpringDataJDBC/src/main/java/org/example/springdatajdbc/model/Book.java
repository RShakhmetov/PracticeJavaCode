package org.example.springdatajdbc.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    private Long id;

    @NotBlank(message = "Title must be initialized")
    private String title;

    @NotNull(message = "Author can`t be null")
    private String author;

    private LocalDate publicationYear;

    public Book(String title, String author, LocalDate publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
