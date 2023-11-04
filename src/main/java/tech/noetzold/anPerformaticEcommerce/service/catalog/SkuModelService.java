package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.SkuModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.SkuModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SkuModelService {

    @Autowired
    SkuModelRepository skuModelRepository;

    public List<SkuModel> findAllSkuModel(){
        return skuModelRepository.findAll();
    }

    public SkuModel findSkuModelById(UUID id){
        return skuModelRepository.findById(id).orElse(null);
    }

    public SkuModel saveSkuModel(SkuModel skuModel){
        return skuModelRepository.save(skuModel);
    }

    public void deleteSkuModel(UUID id){
        skuModelRepository.deleteById(id);
    }
}
