DROP TABLE IF EXISTS authors;

DROP TABLE IF EXISTS books;

CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         firstName VARCHAR(100) NOT NULL,
                         lastName VARCHAR(100) NOT NULL
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       author_id INT,
                       FOREIGN KEY (author_id) REFERENCES authors(id)
);