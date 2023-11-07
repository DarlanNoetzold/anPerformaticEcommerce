package tech.noetzold.anPerformaticEcommerce.service.shipping;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;
import tech.noetzold.anPerformaticEcommerce.repository.shipping.ShippingModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("shipping")
public class ShippingModelService {

    @Autowired
    ShippingModelRepository shippingModelRepository;

    @Transactional
    public List<ShippingModel> findAllShippingModel(){
        return shippingModelRepository.findAll();
    }

    @Transactional
    public ShippingModel findShippingModelById(UUID id){
        return shippingModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public ShippingModel saveShippingModel(ShippingModel shippingModel){
        return shippingModelRepository.save(shippingModel);
    }

    @Transactional
    public void deleteShippingModel(UUID id){
        shippingModelRepository.deleteById(id);
    }
}
