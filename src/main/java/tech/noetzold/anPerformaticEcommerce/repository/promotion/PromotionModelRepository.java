package tech.noetzold.anPerformaticEcommerce.repository.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.promotion.PromotionModel;

import java.util.UUID;

public interface PromotionModelRepository extends JpaRepository<PromotionModel, UUID> {
}
