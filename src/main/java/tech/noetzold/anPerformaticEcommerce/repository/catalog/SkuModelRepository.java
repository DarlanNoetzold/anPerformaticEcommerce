package tech.noetzold.anPerformaticEcommerce.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.catalog.SkuModel;

import java.util.UUID;

public interface SkuModelRepository extends JpaRepository<SkuModel, UUID> {
}
