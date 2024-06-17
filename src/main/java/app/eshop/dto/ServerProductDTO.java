package app.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor

public class ServerProductDTO {

    private Long id;

    private String productName;

    private String description;

    private String image;

    private Integer price;
}
