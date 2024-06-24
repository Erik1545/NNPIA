package app.eshop.controller;

import app.eshop.dto.ProductDTO;
import app.eshop.dto.ServerProductDTO;
import app.eshop.entity.Product;
import app.eshop.service.FileService;
import app.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @PostMapping(consumes = {"multipart/form-data"})
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
