package app.eshop;


import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import app.eshop.service.CartService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = WebApplication.class)
class CartTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;


    @Test
    void addProductToCart(){
        List<Product> productList = productRepository.findAll();
        Long productId = productList.get(3).getId();
        cartService.addToCart(productId);

        Assertions.assertThat(cartService.getCart().size()).isEqualTo(1);
    }

    @Test
    void removeProductsFromCart(){
        List<Product> productList = productRepository.findAll();
        Long productId = productList.get(3).getId();
        cartService.addToCart(productId);
        cartService.addToCart(productId);
        cartService.addToCart(productId);
        Assertions.assertThat(cartService.getCart().containsKey(productList.get(3))).isTrue();

        cartService.removeFromCart(productId);
        cartService.removeFromCart(productId);
        cartService.removeFromCart(productId);
        Assertions.assertThat(cartService.getCart().containsKey(productList.get(3))).isFalse();


    }

    @Test
    void addAndRemoveProducts(){

        List<Product> productList = productRepository.findAll();
        Long productId = productList.get(3).getId();

        cartService.addToCart(productId);

        //v kosiku je polozka 3 prave 1 krat
        Assertions.assertThat(cartService.getCart().get(productList.get(3))).isEqualTo(1);

        cartService.addToCart(productId);

        cartService.addToCart(productId);


        Assertions.assertThat(cartService.getCart().get(productList.get(3))).isEqualTo(3);

        cartService.removeFromCart(productId);
        Assertions.assertThat(cartService.getCart().get(productList.get(3))).isEqualTo(2);

    }
}
