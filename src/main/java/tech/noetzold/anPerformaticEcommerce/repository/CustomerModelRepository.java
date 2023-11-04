package tech.noetzold.anPerformaticEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;

import java.util.UUID;

public interface CustomerModelRepository extends JpaRepository<CustomerModel, UUID> {
}
