package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.ShopCart;

import java.util.UUID;

public interface ShopCartRepository extends JpaRepository<ShopCart, UUID> {
}
