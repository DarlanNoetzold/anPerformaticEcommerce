package tech.noetzold.anPerformaticEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.CustomerModelRepository;

@Service
public class CustomerModelService {

    @Autowired
    CustomerModelRepository customerModelRepository;
}
