package app.eshop.service;
import app.eshop.dto.CartProductDTO;
import java.util.List;


public interface CartService {
    void addToCart(Long id);
    void removeFromCart(Long id);
    List<CartProductDTO> getCart();
}
