package app.eshop.controller;

import app.eshop.dto.CartProductDTO;
import app.eshop.dto.ServerProductDTO;
import app.eshop.entity.Product;
import app.eshop.service.CartService;
import app.eshop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }


    @PostMapping("add/{id}")
    public ResponseEntity<Integer> add(@PathVariable Long id) {
        try {
            cartService.addToCart(id);
            Product product = productService.getProductById(id);
            CartProductDTO cartProductDTO = cartService.getCart().stream().filter(e->e.getProduct().equals(product)).findFirst().orElseThrow();

            Integer quantity = cartProductDTO.getQuantity();
            return ResponseEntity.ok(quantity);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("remove/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        try {
            cartService.removeFromCart(id);
            Product product = productService.getProductById(id);
            CartProductDTO cartProductDTO = cartService.getCart().stream().filter(e->e.getProduct().equals(product)).findFirst().orElseThrow();

            Integer quantity = cartProductDTO.getQuantity();
            return ResponseEntity.ok(quantity);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping
    public List<CartProductDTO> getCart() {
        List<CartProductDTO> cart = cartService.getCart();
        return cart;
    }


}
