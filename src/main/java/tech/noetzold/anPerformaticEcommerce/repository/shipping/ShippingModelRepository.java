package tech.noetzold.anPerformaticEcommerce.repository.shipping;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;

import java.util.UUID;

public interface ShippingModelRepository extends JpaRepository<ShippingModel, UUID> {
}
