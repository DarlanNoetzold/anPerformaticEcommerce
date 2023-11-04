package tech.noetzold.anPerformaticEcommerce.service.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.promotion.PromotionModel;
import tech.noetzold.anPerformaticEcommerce.repository.promotion.PromotionModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PromotionModelService {

    @Autowired
    PromotionModelRepository promotionModelRepository;

    public List<PromotionModel> findAllPromotionModel(){
        return promotionModelRepository.findAll();
    }

    public PromotionModel findPromotionModelById(UUID id){
        return promotionModelRepository.findById(id).orElse(null);
    }

    public PromotionModel savePromotionModel(PromotionModel promotionModel){
        return promotionModelRepository.save(promotionModel);
    }

    public void deletePromotionModel(UUID id){
        promotionModelRepository.deleteById(id);
    }
}
