package app.eshop.dto;

import lombok.Getter;
import lombok.Setter;

public class AddProductDTO {

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private Long id;
}
