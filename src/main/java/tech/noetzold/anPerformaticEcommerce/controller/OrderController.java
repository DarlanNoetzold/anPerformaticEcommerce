package tech.noetzold.anPerformaticEcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;
}
