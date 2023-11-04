package tech.noetzold.anPerformaticEcommerce.service.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.promotion.PromotionModelRepository;

@Service
public class PromotionModelService {

    @Autowired
    PromotionModelRepository promotionModelRepository;
}
