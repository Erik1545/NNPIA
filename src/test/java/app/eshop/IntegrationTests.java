package app.eshop;


import app.eshop.entity.CustomerOrder;
import app.eshop.entity.CustomerOrder_Product;
import app.eshop.entity.Product;
import app.eshop.service.CartService;
import app.eshop.service.CustomerOrderService;
import app.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = WebApplication.class)
public class IntegrationTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerOrderService customerOrderService;


    @Test
    void customerOrderCreation(){
        cartService.addToCart(1L);
        cartService.addToCart(1L);
        cartService.addToCart(2L);
        String username = "Test";
        customerOrderService.check(username);
        List<CustomerOrder> customerOrders = customerOrderService.getCustomerOrders(username);

        assertEquals(1, customerOrders.size());
        CustomerOrder customerOrder = customerOrders.get(0);
        Set<CustomerOrder_Product> customerOrder_products = customerOrder.getCustomerOrderProducts();

        assertEquals(2, customerOrder_products.size());

        CustomerOrder_Product product1 = customerOrder_products.stream().filter(e -> e.getProduct().getId().equals(1L)).findFirst().orElseThrow();
        assertEquals(2, product1.getQuantity());
        CustomerOrder_Product product2 = customerOrder_products.stream().filter(e -> e.getProduct().getId().equals(2L)).findFirst().orElseThrow();
        assertEquals(1, product2.getQuantity());
    }
}
