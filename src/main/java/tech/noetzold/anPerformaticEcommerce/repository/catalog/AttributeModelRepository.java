package tech.noetzold.anPerformaticEcommerce.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.catalog.AttributeModel;

import java.util.UUID;

public interface AttributeModelRepository extends JpaRepository<AttributeModel, UUID> {
}
