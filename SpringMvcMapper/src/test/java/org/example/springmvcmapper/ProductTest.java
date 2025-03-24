package org.example.springmvcmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springmvcmapper.controller.ProductController;
import org.example.springmvcmapper.models.Product;
import org.example.springmvcmapper.repositories.ProductRepository;
import org.example.springmvcmapper.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product.");
        product.setPrice(99.99);
        product.setQuantityInStock(10);

        String productJson = "{" +
                "\"productId\":1," +
                "\"name\":\"Test Product\"," +
                "\"description\":\"This is a test product.\"," +
                "\"price\":99.99," +
                "\"quantityInStock\":10" +
                "}";

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        when(objectMapper.writeValueAsString(product)).thenReturn(productJson);

        assertThat(objectMapper.writeValueAsString(product)).isEqualTo(productService.getProductById(product.getProductId()));
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product.");
        product.setPrice(99.99);
        product.setQuantityInStock(10);

        String productJson = "{\n" +
                "  \"productId\" : 1,\n" +
                "  \"name\" : \"Test Product\",\n" +
                "  \"description\" : \"This is a test product.\",\n" +
                "  \"price\" : 99.99,\n" +
                "  \"quantityInStock\" : 10\n" +
                "}";

        when(objectMapper.readValue(productJson, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        assertThat(objectMapper.readValue(productJson, Product.class)).isEqualTo(product);
    }

}
