package tech.noetzold.anPerformaticEcommerce.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.CardModel;

import java.util.UUID;

public interface CardModelRepository extends JpaRepository<CardModel, UUID> {
}
