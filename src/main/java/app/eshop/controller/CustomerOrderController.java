package app.eshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CustomerOrderController {
    @GetMapping("/check")
    public String check(Model model){
        return "redirect:/";

    }
}
