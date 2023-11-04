package tech.noetzold.anPerformaticEcommerce.service.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.promotion.CouponModelRepository;

@Service
public class CouponModelService {

    @Autowired
    CouponModelRepository couponModelRepository;
}
