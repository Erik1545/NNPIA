package app.eshop.repository;

import app.eshop.entity.CustomerOrder;
import app.eshop.entity.CustomerOrder_Product;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;


//misto Long by mel bys slozeny klic
public interface CustomerOrder_ProductRepository extends JpaRepository<CustomerOrder_Product, Long> {
}
