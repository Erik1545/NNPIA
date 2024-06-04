package app.eshop.runner;

import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class WebRunner implements ApplicationRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        productRepository.save(new Product(1, "Erik"));
        productRepository.save(new Product(2, "Honza"));

    }
}
