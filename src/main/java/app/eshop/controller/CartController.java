package app.eshop.controller;

import app.eshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, Model model){
        cartService.addToCart(id);
        return "redirect:/cart";
    }
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id, Model model){
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String showCart(Model model){
        model.addAttribute("cart", cartService.getCart());
        return "/cart";
    }
}
