package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.BoletoModel;

import java.util.UUID;

public interface BoletoModelRepository extends JpaRepository<BoletoModel, UUID> {
}
