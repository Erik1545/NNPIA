package app.eshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private CustomerOrderStateEnum state;

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER)
    private Set<CustomerOrder_Product> customerOrderProducts;

}
