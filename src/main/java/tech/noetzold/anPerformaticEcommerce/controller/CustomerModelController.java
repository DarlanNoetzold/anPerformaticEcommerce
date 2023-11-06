package tech.noetzold.anPerformaticEcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.CustomerModelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/customer")
public class CustomerModelController {

    @Autowired
    CustomerModelService customerModelService;
}
