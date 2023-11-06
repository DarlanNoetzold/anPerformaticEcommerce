package tech.noetzold.anPerformaticEcommerce.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.payment.InvoiceModelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/payment/invoice")
public class InvoiceModelController {

    @Autowired
    InvoiceModelService invoiceModelService;
}
