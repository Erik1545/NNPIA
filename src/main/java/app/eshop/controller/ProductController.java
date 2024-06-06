package app.eshop.controller;


import app.eshop.dto.AddProductDTO;
import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String getAllProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/addproduct")
    public String addProduct(Model model){
        model.addAttribute("addNewProduct", new AddProductDTO());
        return "add_product_form";
    }

    @PostMapping("/addproductaccepted")
    public String addProductAccepted(AddProductDTO addProductDTO, Model model){
        Product addNewProduct = new Product();
        addNewProduct.setName(addProductDTO.getProductName());
        productRepository.save(addNewProduct);
        return "redirect:/";
    }
}
