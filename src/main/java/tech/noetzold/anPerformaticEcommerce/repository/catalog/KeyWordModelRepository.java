package tech.noetzold.anPerformaticEcommerce.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.catalog.KeyWordModel;

import java.util.UUID;

public interface KeyWordModelRepository extends JpaRepository<KeyWordModel, UUID> {
}
