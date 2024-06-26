package app.eshop.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
