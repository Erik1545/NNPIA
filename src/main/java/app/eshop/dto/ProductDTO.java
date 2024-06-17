package app.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor

public class ProductDTO {

    private String productName;

    private String description;

    private MultipartFile image;

    private Integer price;
}
