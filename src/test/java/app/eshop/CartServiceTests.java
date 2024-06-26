package app.eshop;
import app.eshop.dto.CartProductDTO;
import app.eshop.entity.Product;
import app.eshop.repository.ProductRepository;
import app.eshop.service.CartService;
import app.eshop.service.CartServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WebApplication.class)
class CartServiceTests {

    private ProductRepository productRepository;
    private CartService cartService;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        cartService = new CartServiceImpl(productRepository);
    }

    @Test
    void removeAllProductsFromCart() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        cartService.addToCart(1L);
        cartService.addToCart(1L);
        cartService.addToCart(1L);

        List<CartProductDTO> cart = cartService.getCart();
        Optional<CartProductDTO> cartProductDTO = cart.stream().filter(e -> e.getProduct().equals(product)).findFirst();
        Assertions.assertThat(cartProductDTO.isPresent()).isTrue();
        Assertions.assertThat(cartProductDTO.get().getQuantity()).isEqualTo(3);

        cartService.removeFromCart(1L);
        cartService.removeFromCart(1L);
        cartService.removeFromCart(1L);

        cart = cartService.getCart();
        cartProductDTO = cart.stream().filter(e -> e.getProduct().equals(product)).findFirst();
        Assertions.assertThat(cartProductDTO.isPresent()).isFalse();
    }

    @Test
    void addAndRemoveProducts() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        cartService.addToCart(1L);

        List<CartProductDTO> cart = cartService.getCart();
        Optional<CartProductDTO> cartProductDTO = cart.stream().filter(e -> e.getProduct().equals(product)).findFirst();
        Assertions.assertThat(cartProductDTO.isPresent()).isTrue();
        Assertions.assertThat(cartProductDTO.get().getQuantity()).isEqualTo(1);

        cartService.addToCart(1L);
        cartService.addToCart(1L);

        cart = cartService.getCart();
        cartProductDTO = cart.stream().filter(e -> e.getProduct().equals(product)).findFirst();
        Assertions.assertThat(cartProductDTO.isPresent()).isTrue();
        Assertions.assertThat(cartProductDTO.get().getQuantity()).isEqualTo(3);

        cartService.removeFromCart(1L);

        cart = cartService.getCart();
        cartProductDTO = cart.stream().filter(e -> e.getProduct().equals(product)).findFirst();
        Assertions.assertThat(cartProductDTO.isPresent()).isTrue();
        Assertions.assertThat(cartProductDTO.get().getQuantity()).isEqualTo(2);
    }
}
