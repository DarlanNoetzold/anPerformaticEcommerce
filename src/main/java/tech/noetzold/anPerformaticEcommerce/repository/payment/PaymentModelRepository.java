package tech.noetzold.anPerformaticEcommerce.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;

import java.util.UUID;

public interface PaymentModelRepository extends JpaRepository<PaymentModel, UUID> {
}
