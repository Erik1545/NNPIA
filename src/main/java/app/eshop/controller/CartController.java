package app.eshop.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CartController {

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, Model model){
        return "redirect:/cart";
    }
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id, Model model){
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String showCart(@PathVariable Long id, Model model){
        return "cart";
    }
}
