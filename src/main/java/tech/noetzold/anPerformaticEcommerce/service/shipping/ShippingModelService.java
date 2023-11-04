package tech.noetzold.anPerformaticEcommerce.service.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;
import tech.noetzold.anPerformaticEcommerce.repository.shipping.ShippingModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ShippingModelService {

    @Autowired
    ShippingModelRepository shippingModelRepository;

    public List<ShippingModel> findAllShippingModel(){
        return shippingModelRepository.findAll();
    }

    public ShippingModel findShippingModelById(UUID id){
        return shippingModelRepository.findById(id).orElse(null);
    }

    public ShippingModel saveShippingModel(ShippingModel shippingModel){
        return shippingModelRepository.save(shippingModel);
    }

    public void deleteShippingModel(UUID id){
        shippingModelRepository.deleteById(id);
    }
}
