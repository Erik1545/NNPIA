package app.eshop.service;
import app.eshop.dto.ProductDTO;
import app.eshop.dto.ServerProductDTO;
import app.eshop.entity.Product;
import java.util.List;

public interface ProductService {

    List<ServerProductDTO> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy);

    ServerProductDTO getProductDTOById(Long id);

    Product getProductById(Long id);
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
