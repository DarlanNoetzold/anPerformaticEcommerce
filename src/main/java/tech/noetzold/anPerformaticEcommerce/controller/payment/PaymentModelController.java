package tech.noetzold.anPerformaticEcommerce.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.payment.PaymentModelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/payment/payment")
public class PaymentModelController {

    @Autowired
    PaymentModelService paymentModelService;
}
