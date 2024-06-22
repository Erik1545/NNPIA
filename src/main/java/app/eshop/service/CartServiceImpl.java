package app.eshop.service;


import app.eshop.dto.CartProductDTO;
import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
//@SessionScope
public class CartServiceImpl implements CartService {

    private final List<CartProductDTO> cart;

    private final ProductRepository productRepository;

    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        cart = new ArrayList<>();
    }

    @Override
    public void addToCart(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoClassDefFoundError::new);
        Optional<CartProductDTO> cartProductDTO = cart.stream().filter(e->e.getProduct().equals(product)).findFirst();
        if(cartProductDTO.isPresent()){
            cartProductDTO.get().setQuantity(cartProductDTO.get().getQuantity()+1);
        }
        else{
            cart.add(new CartProductDTO(product, 1));
        }

    }

    @Override
    public void removeFromCart(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoClassDefFoundError::new);
        Optional<CartProductDTO> cartProductDTO = cart.stream().filter(e->e.getProduct().equals(product)).findFirst();
        if(cartProductDTO.isPresent()){
            cartProductDTO.get().setQuantity(cartProductDTO.get().getQuantity()-1);
            if(cartProductDTO.get().getQuantity() == 0){
                cart.remove(cartProductDTO.get());
            }
        }

    }

    @Override
    public List<CartProductDTO> getCart() {
        return cart;
    }
}
