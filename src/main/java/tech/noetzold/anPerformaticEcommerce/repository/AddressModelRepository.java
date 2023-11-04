package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.shipping.AddressModel;

import java.util.UUID;

public interface AddressModelRepository extends JpaRepository<AddressModel, UUID> {
}
