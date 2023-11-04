package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.AttributeModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.AttributeModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AttributeModelService {

    @Autowired
    AttributeModelRepository attributeModelRepository;

    public List<AttributeModel> findAllAttributeModel(){
        return attributeModelRepository.findAll();
    }

    public AttributeModel findAttributeModelById(UUID id){
        return attributeModelRepository.findById(id).orElse(null);
    }

    public AttributeModel saveAttributeModel(AttributeModel attributeModel){
        return attributeModelRepository.save(attributeModel);
    }

    public void deleteAttributeModel(UUID id){
        attributeModelRepository.deleteById(id);
    }
}
