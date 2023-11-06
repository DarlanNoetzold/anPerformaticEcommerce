package tech.noetzold.anPerformaticEcommerce.controller.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.shipping.AddressModelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/shipping/address")
public class AddressModelController {

    @Autowired
    AddressModelService addressModelService;
}
