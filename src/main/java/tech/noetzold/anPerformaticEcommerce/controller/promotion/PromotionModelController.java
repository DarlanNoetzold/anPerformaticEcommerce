package tech.noetzold.anPerformaticEcommerce.controller.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.promotion.PromotionModelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/promo/promotion")
public class PromotionModelController {

    @Autowired
    PromotionModelService promotionModelService;
}
