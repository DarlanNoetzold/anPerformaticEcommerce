package tech.noetzold.anPerformaticEcommerce.service.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.promotion.CouponModel;
import tech.noetzold.anPerformaticEcommerce.repository.promotion.CouponModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CouponModelService {

    @Autowired
    CouponModelRepository couponModelRepository;

    public List<CouponModel> findAllCouponModel(){
        return couponModelRepository.findAll();
    }

    public CouponModel findCouponModelById(UUID id){
        return couponModelRepository.findById(id).orElse(null);
    }

    public CouponModel saveCouponModel(CouponModel couponModel){
        return couponModelRepository.save(couponModel);
    }

    public void deleteCouponModel(UUID id){
        couponModelRepository.deleteById(id);
    }
}
