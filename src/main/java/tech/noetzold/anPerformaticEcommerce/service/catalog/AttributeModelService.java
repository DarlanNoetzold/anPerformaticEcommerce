package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.noetzold.anPerformaticEcommerce.model.catalog.AttributeModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.AttributeModelRepository;

import java.util.List;
import java.util.UUID;


@Cacheable("attribute")
@Service
public class AttributeModelService {

    @Autowired
    AttributeModelRepository attributeModelRepository;

    @Transactional
    public List<AttributeModel> findAllAttributeModel(){
        return attributeModelRepository.findAll();
    }

    @Transactional
    public AttributeModel findAttributeModelById(UUID id){
        return attributeModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public AttributeModel updateAttributeModel(UUID id, AttributeModel attributeModel){
        attributeModel.setAttributeId(id);
        return attributeModelRepository.save(attributeModel);
    }

    public AttributeModel saveAttributeModel(AttributeModel attributeModel){
        return attributeModelRepository.save(attributeModel);
    }

    @Transactional
    public void deleteAttributeModel(UUID id){
        attributeModelRepository.deleteById(id);
    }
}
