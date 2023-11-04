package tech.noetzold.anPerformaticEcommerce.service.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.shipping.AddressModelRepository;

@Service
public class AddressModelService {

    @Autowired
    AddressModelRepository addressModelRepository;
}
