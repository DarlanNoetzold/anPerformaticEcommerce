package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.payment.PaypalModelRepository;

@Service
public class PaypalModelService {

    @Autowired
    PaypalModelRepository paypalModelRepository;
}
