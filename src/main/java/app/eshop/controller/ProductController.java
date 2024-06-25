package app.eshop.controller;

import app.eshop.dto.ProductDTO;
import app.eshop.dto.ServerProductDTO;
import app.eshop.entity.Product;
import app.eshop.service.FileService;
import app.eshop.service.ProductService;
import app.eshop.validation.ValidImage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @GetMapping
    public List<ServerProductDTO> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "2") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
        return productService.getAllProducts(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public ServerProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductDTOById(id);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product createProduct(@RequestParam("image") @ValidImage MultipartFile image,
                                 @RequestParam("productName") @NotNull String productName,
                                 @RequestParam("description") @Size(max = 50) String description,
                                 @RequestParam("price") @Min(1) Integer price) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(productName);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setImage(image);
        return productService.createProduct(productDTO);
    }

    @PutMapping(path="/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@PathVariable Long id, @RequestParam("image")MultipartFile image, @RequestParam("productName") String productName, @RequestParam("description") String description, @RequestParam("price") Integer price) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(productName);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setImage(image);
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
