package app.eshop.service;

import app.eshop.dto.CartProductDTO;
import app.eshop.entity.CustomerOrder;
import app.eshop.entity.CustomerOrderStateEnum;
import app.eshop.entity.CustomerOrder_Product;
import app.eshop.entity.Product;
import app.eshop.repository.CustomerOrderRepository;
import app.eshop.repository.CustomerOrder_ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Map;


@Service
@SessionScope
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService{

    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerOrder_ProductRepository customerOrder_productRepository;
    private final CartService cartService;


    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository, CustomerOrder_ProductRepository customerOrder_productRepository, CartService cartService) {
        this.customerOrderRepository = customerOrderRepository;
        this.customerOrder_productRepository = customerOrder_productRepository;
        this.cartService = cartService;
    }

    @Override
    public void check(String username) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setState(CustomerOrderStateEnum.NEW);
        customerOrder.setUsername(username);
        customerOrderRepository.save(customerOrder);


        for (CartProductDTO entry : cartService.getCart()) {
            CustomerOrder_Product customerOrder_product = new CustomerOrder_Product();
            customerOrder_product.setCustomerOrder(customerOrder);
            customerOrder_product.setUsername(username);
            customerOrder_product.setProduct(entry.getProduct());
            customerOrder_product.setQuantity(entry.getQuantity());
            customerOrder_productRepository.save(customerOrder_product);

        }
        cartService.getCart().clear();

    }

    @Override
    public List<CustomerOrder> getCustomerOrders(String username){
        return customerOrderRepository.findAllByUsername(username);
    }
}
