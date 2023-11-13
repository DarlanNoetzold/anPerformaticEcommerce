package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.OrderModel;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderModel, UUID> {
}
