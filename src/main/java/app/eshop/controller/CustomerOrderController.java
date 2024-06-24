package app.eshop.controller;


import app.eshop.entity.CustomerOrder;
import app.eshop.security.JWTUtilities;
import app.eshop.service.CustomerOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/order")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;
    private JWTUtilities jwtUtilities;

    public CustomerOrderController(CustomerOrderService customerOrderService, JWTUtilities jwtUtilities) {
        this.customerOrderService = customerOrderService;
        this.jwtUtilities = jwtUtilities;
    }

    @PostMapping
    public ResponseEntity<String> check(@RequestHeader("Authorization") String token){
        try {
            customerOrderService.check(jwtUtilities.extractUsername(token.substring(7)));
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
