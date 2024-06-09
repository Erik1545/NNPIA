package app.eshop.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class AddProductDTO {

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private MultipartFile image;

    @Getter
    @Setter
    private Long id;
}
