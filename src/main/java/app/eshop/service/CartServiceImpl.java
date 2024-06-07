package app.eshop.service;


import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartServiceImpl implements CartService {

    private final Map<Product, Integer> cart;

    private final ProductRepository productRepository;

    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        cart = new HashMap<>();
    }

    @Override
    public void addToCart(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoClassDefFoundError::new);
        if(cart.containsKey(product)){
            cart.replace(product, cart.get(product)+1);
        }
        else {
            cart.put(product, 1);
        }

    }

    @Override
    public void removeFromCart(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoClassDefFoundError::new);
        if(cart.containsKey(product)) {
            if(cart.get(product)>1){
                cart.replace(product, cart.get(product)-1);
            }
            else{
                cart.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }
}
