package tech.noetzold.anPerformaticEcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.CommerceItemService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/commerceitem")
public class CommerceItemController {

    @Autowired
    CommerceItemService commerceItemService;
}
