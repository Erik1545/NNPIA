package app.eshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

    @NotNull
    private String productName;

    @Size(max = 50)
    private String description;

    private MultipartFile image;

    @Min(1)
    private Integer price;
}
