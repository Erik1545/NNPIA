package app.eshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    @Column(columnDefinition = "text")
    private String description;

    @Getter
    @Setter
    private Integer price;

    @Getter
    @Setter
    private String imagePath;

    @OneToMany(mappedBy = "id")
    @Getter
    @Setter
    private Set<CustomerOrder_Product> productInCustomerOrders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName);
    }
}
