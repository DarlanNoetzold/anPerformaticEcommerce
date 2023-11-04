package tech.noetzold.anPerformaticEcommerce.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.catalog.ProductModel;

import java.util.UUID;

public interface ProductModelRepository extends JpaRepository<ProductModel, UUID> {
}
