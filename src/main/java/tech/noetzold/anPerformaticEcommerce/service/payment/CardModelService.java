package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.payment.CardModelRepository;

@Service
public class CardModelService {

    @Autowired
    CardModelRepository cardModelRepository;
}
