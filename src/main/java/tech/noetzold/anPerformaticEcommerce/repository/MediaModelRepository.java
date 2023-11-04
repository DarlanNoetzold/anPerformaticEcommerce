package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.catalog.MediaModel;

import java.util.UUID;

public interface MediaModelRepository extends JpaRepository<MediaModel, UUID> {
}
