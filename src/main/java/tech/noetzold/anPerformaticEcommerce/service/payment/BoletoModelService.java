package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.payment.BoletoModelRepository;

@Service
public class BoletoModelService {

    @Autowired
    BoletoModelRepository boletoModelRepository;
}
