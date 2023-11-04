package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.CommerceItem;

import java.util.UUID;

public interface CommerceItemRepository extends JpaRepository<CommerceItem, UUID> {
}
