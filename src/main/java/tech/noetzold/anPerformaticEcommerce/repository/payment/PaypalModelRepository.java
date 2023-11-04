package tech.noetzold.anPerformaticEcommerce.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PaypalModel;

import java.util.UUID;

public interface PaypalModelRepository extends JpaRepository<PaypalModel, UUID> {
}
