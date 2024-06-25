package app.eshop.service;

import app.eshop.controller.NotFoundException;
import app.eshop.dto.ProductDTO;
import app.eshop.dto.ServerProductDTO;
import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileService fileService;

    @Override
    public List<ServerProductDTO> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortBy);
        return productRepository.findAll(pageable).stream()
                .map(product -> new ServerProductDTO(product.getId(), product.getProductName(), product.getDescription(), product.getImagePath(), product.getPrice()))
                .toList();
    }

    @Override
    public ServerProductDTO getProductDTOById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ServerProductDTO(product.getId(), product.getProductName(), product.getDescription(), product.getImagePath(), product.getPrice())).orElseThrow(() -> new NotFoundException(String.format("Product %s not found", id)));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product %s not found", id)));
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product newProduct = new Product();
        newProduct.setProductName(productDTO.getProductName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setPrice(productDTO.getPrice());

        if (productDTO.getImage() != null) {
            String imageName = fileService.upload(productDTO.getImage());
            newProduct.setImagePath(imageName);
        }

        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductName(productDTO.getProductName());
                    existingProduct.setDescription(productDTO.getDescription());
                    existingProduct.setPrice(productDTO.getPrice());

                    if (productDTO.getImage() != null) {
                        String imageName = fileService.upload(productDTO.getImage());
                        existingProduct.setImagePath(imageName);
                    }

                    return productRepository.save(existingProduct);
                }).orElseThrow(() -> new NotFoundException(String.format("Product %s not found", id)));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product %s not found", id)));
        productRepository.delete(product);
    }
}
