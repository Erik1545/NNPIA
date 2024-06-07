package app.eshop.runner;

import app.eshop.entity.CustomerOrder;
import app.eshop.entity.CustomerOrderStateEnum;
import app.eshop.entity.CustomerOrder_Product;
import app.eshop.entity.Product;
import app.eshop.repository.CustomerOrderRepository;
import app.eshop.repository.CustomerOrder_ProductRepository;
import app.eshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class WebRunner implements ApplicationRunner {


    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    private final CustomerOrder_ProductRepository customerOrderProductRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Product product1 = new Product();
        product1.setProductName("Iron-man");

        Product product2 = new Product();
        product2.setProductName("Spiderman");

        Product product3 = new Product();
        product3.setProductName("Black-Widow");

        Product product4 = new Product();
        product4.setProductName("Kapitan-Amerika");

        Product product5 = new Product();
        product5.setProductName("Groot");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setState(CustomerOrderStateEnum.NEW);
        customerOrderRepository.save(customerOrder);

        CustomerOrder_Product customerOrder_product = new CustomerOrder_Product();
        customerOrder_product.setProduct(product1);
        customerOrder_product.setCustomerOrder(customerOrder);
        customerOrder_product.setQuantity(5);
        customerOrderProductRepository.save(customerOrder_product);

    }
}
