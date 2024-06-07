package app.eshop.controller;


import app.eshop.dto.AddProductDTO;
import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @ExceptionHandler(RuntimeException.class)
    public String handleError(Model model){
        return "error";
    }

    @GetMapping("/")
    public String getAllProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping(value={"/addproduct","/addproduct/{id}"})
    public String addProduct(@PathVariable(required = false) Long id, Model model){
        if(id != null){
            Product findById = productRepository.findById(id).orElse(new Product());
            model.addAttribute("addNewProduct", findById);
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
        productRepository.save(addNewProduct);
        return "redirect:/";
    }

    @GetMapping("/productdetail/{id}")
    public String displayDetails(@PathVariable(required = false) Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).get());
        return "product_detail";
    }
}
