package tech.noetzold.anPerformaticEcommerce.service.catalog;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.KeyWordModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.KeyWordModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("keyword")
public class KeyWordModelService {

    @Autowired
    KeyWordModelRepository keyWordModelRepository;

    public List<KeyWordModel> findAllKeyWordModel(){
        return keyWordModelRepository.findAll();
    }

    public KeyWordModel findKeyWordModelById(UUID id){
        return keyWordModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public KeyWordModel saveKeyWordModel(KeyWordModel keyWordModel){
        return keyWordModelRepository.save(keyWordModel);
    }

    @Transactional
    public void deleteKeyWordModel(UUID id){
        keyWordModelRepository.deleteById(id);
    }
}
