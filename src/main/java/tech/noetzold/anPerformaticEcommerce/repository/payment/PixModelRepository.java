package tech.noetzold.anPerformaticEcommerce.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PixModel;

import java.util.UUID;

public interface PixModelRepository extends JpaRepository<PixModel, UUID> {
}
