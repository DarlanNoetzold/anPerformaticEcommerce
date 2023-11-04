package tech.noetzold.anPerformaticEcommerce.repository.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.promotion.CouponModel;

import java.util.UUID;

public interface CouponModelRepository extends JpaRepository<CouponModel, UUID> {
}
