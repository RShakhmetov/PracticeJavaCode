package org.example.springmvclibrary.repository;

import org.example.springmvclibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
