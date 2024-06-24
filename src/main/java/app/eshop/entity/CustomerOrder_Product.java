package app.eshop.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class CustomerOrder_Product {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String username;

    @ManyToOne
    @Getter
    @Setter
    private CustomerOrder customerOrder;


    @ManyToOne
    @Getter
    @Setter
    private Product product;

    @Getter
    @Setter
    private Integer quantity;
}
