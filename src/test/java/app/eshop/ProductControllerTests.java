package app.eshop;


import app.eshop.dto.ProductDTO;
import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;


    @Test
    void createNewProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Erik");
        productDTO.setDescription("student UPCE");
        productDTO.setPrice(101);
        MockMultipartFile image = new MockMultipartFile("image", "test-image.jpg", MediaType.IMAGE_JPEG_VALUE, "test image content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/products")
                        .file(image)
                        .param("productName", productDTO.getProductName())
                        .param("description", productDTO.getDescription())
                        .param("price", String.valueOf(productDTO.getPrice()))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Is.is(productDTO.getProductName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is(productDTO.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(101)));
    }

    @Test
    void createNewProductWithInvalidImage() throws ConstraintViolationException {

        MockMultipartFile invalidImage = new MockMultipartFile("image", "test-invalid-image.txt", MediaType.TEXT_PLAIN_VALUE, "invalid image content".getBytes());

        ConstraintViolationException exception = new ConstraintViolationException("createProduct.image: Spatny format obrazku", null);

        try {
            mockMvc.perform(MockMvcRequestBuilders.multipart("/api/products")
                            .file(invalidImage)
                            .param("productName", "Erik")
                            .param("description", "student UPCE")
                            .param("price", "100")
                            .contentType(MediaType.MULTIPART_FORM_DATA));
        }
        catch (Exception e){
            assertEquals(e.getCause().getMessage(), exception.getMessage());
        }
    }


    @Test
    void getProductById() throws Exception{
        Product product = new Product();
        product.setProductName("Ilona");
        product.setDescription("studentka");
        product.setImagePath("");
        product.setPrice(450);
        Product savedProduct = productRepository.save(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{id}", savedProduct.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(Integer.parseInt(savedProduct.getId().toString()))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Is.is(product.getProductName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is(product.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image", Is.is(product.getImagePath())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(product.getPrice())))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
