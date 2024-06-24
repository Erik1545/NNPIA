package app.eshop.repository;

import app.eshop.entity.CustomerOrder;
import app.eshop.entity.CustomerOrder_Product;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerOrder_ProductRepository extends JpaRepository<CustomerOrder_Product, Long> {
}
