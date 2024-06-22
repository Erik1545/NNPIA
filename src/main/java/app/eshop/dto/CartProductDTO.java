package app.eshop.dto;

import app.eshop.entity.Product;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {

    private Product product;
    private Integer quantity;

    @JsonGetter("product")
    public ServerProductDTO getProductForJson() {
        return mapToServerProductDTO();
    }

    private ServerProductDTO mapToServerProductDTO() {
        if (product == null) {
            return null;
        }
        return new ServerProductDTO(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getImagePath(),
                product.getPrice()
        );
    }
}
