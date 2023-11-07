package tech.noetzold.anPerformaticEcommerce.service.catalog;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.SkuModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.SkuModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("sku")
public class SkuModelService {

    @Autowired
    SkuModelRepository skuModelRepository;

    @Transactional
    public List<SkuModel> findAllSkuModel(){
        return skuModelRepository.findAll();
    }

    @Transactional
    public SkuModel findSkuModelById(UUID id){
        return skuModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public SkuModel saveSkuModel(SkuModel skuModel){
        return skuModelRepository.save(skuModel);
    }

    @Transactional
    public void deleteSkuModel(UUID id){
        skuModelRepository.deleteById(id);
    }
}
