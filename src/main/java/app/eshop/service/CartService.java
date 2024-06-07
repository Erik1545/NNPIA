package app.eshop.service;

import app.eshop.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface CartService {

    void addToCart(Long id);
    void removeFromCart(Long id);
    Map<Product, Integer> getCart();
}
