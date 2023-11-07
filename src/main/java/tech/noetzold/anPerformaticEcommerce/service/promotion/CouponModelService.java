package tech.noetzold.anPerformaticEcommerce.service.promotion;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.promotion.CouponModel;
import tech.noetzold.anPerformaticEcommerce.repository.promotion.CouponModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("coupon")
public class CouponModelService {

    @Autowired
    CouponModelRepository couponModelRepository;

    @Transactional
    public List<CouponModel> findAllCouponModel(){
        return couponModelRepository.findAll();
    }

    @Transactional
    public CouponModel findCouponModelById(UUID id){
        return couponModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public CouponModel saveCouponModel(CouponModel couponModel){
        return couponModelRepository.save(couponModel);
    }

    @Transactional
    public void deleteCouponModel(UUID id){
        couponModelRepository.deleteById(id);
    }
}
