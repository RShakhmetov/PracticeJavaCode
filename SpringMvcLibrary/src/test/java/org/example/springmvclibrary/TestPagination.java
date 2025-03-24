package org.example.springmvclibrary;

import org.example.springmvclibrary.model.Book;
import org.example.springmvclibrary.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPagination {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        pageable = PageRequest.of(0, 2);
    }

    @Test
    public void testGetBooksWithPagination() throws Exception {
        // Подготовка тестовых данных
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Капитанская дочка");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("1984");

        Page<Book> page = new PageImpl<>(Arrays.asList(book1, book2), pageable, 4);

        // Мокаем сервис
        when(bookService.getAllBooks(pageable)).thenReturn(page);

        // Выполняем запрос и проверяем результаты
        mockMvc.perform(get("/api/v1/getBooks?page=0&size=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.totalElements").value(2));
    }
}
