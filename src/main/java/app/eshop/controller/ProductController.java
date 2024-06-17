package app.eshop.controller;


import app.eshop.dto.ProductDTO;
import app.eshop.dto.ServerProductDTO;
import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import app.eshop.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileService fileService;

    @ExceptionHandler(RuntimeException.class)
    public HttpStatus handleError(){
        return HttpStatus.NOT_FOUND;
    }

    @GetMapping("/")
    public List<ServerProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ServerProductDTO(product.getId(), product.getProductName(), product.getDescription(), product.getImagePath(), product.getPrice())).toList();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = new Product();
        newProduct.setProductName(productDTO.getProductName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setPrice(productDTO.getPrice());

        String imageName = fileService.upload(productDTO.getImage());
        newProduct.setImagePath(imageName);

        Product savedProduct = productRepository.save(newProduct);
        return savedProduct;
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setProductName(productDTO.getProductName());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setPrice(productDTO.getPrice());

            if (productDTO.getImage() != null) {
                String imageName = fileService.upload(productDTO.getImage());
                existingProduct.setImagePath(imageName);
            }

            return productRepository.save(existingProduct);
        }).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        productRepository.delete(product);
    }

    /*



    @GetMapping(value={"/addproduct","/addproduct/{id}"})
    public String addProduct(@PathVariable(required = false) Long id, Model model){
        if(id != null){
            Product findById = productRepository.findById(id).orElse(new Product());

            AddProductDTO addProductDTO = new AddProductDTO();
            addProductDTO.setId(findById.getId());
            addProductDTO.setProductName(findById.getProductName());
            addProductDTO.setDescription(findById.getDescription());


            model.addAttribute("addNewProduct", addProductDTO);
            return "add_product_form";
        }
        else {
            model.addAttribute("addNewProduct", new AddProductDTO());
            return "add_product_form";
        }

    }

    @PostMapping("/addproductaccepted")
    public String addProductAccepted(AddProductDTO addProductDTO, Model model){
        Product addNewProduct = new Product();
        addNewProduct.setId(addProductDTO.getId());
        addNewProduct.setProductName(addProductDTO.getProductName());
        addNewProduct.setDescription(addProductDTO.getDescription());

        String imageName = fileService.upload(addProductDTO.getImage());
        addNewProduct.setImagePath(imageName);
        productRepository.save(addNewProduct);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String displayDetails(@PathVariable(required = false) Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).get());
        return "detail";
    }

     */


}
