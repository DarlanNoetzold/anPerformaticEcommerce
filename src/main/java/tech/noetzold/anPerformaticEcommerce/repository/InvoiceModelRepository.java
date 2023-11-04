package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.payment.InvoiceModel;

import java.util.UUID;

public interface InvoiceModelRepository extends JpaRepository<InvoiceModel, UUID> {
}
