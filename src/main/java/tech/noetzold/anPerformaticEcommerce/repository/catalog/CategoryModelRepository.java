package tech.noetzold.anPerformaticEcommerce.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.catalog.CategoryModel;

import java.util.UUID;

public interface CategoryModelRepository extends JpaRepository<CategoryModel, UUID> {
}
