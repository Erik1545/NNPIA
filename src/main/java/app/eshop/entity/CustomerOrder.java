package app.eshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class CustomerOrder {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private CustomerOrderStateEnum state;


    @OneToMany(mappedBy = "id")
    @Getter
    @Setter
    private Set<CustomerOrder_Product> customerOrderProducts;

}
