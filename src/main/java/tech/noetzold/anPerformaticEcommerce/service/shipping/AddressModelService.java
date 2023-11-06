package tech.noetzold.anPerformaticEcommerce.service.shipping;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.shipping.AddressModel;
import tech.noetzold.anPerformaticEcommerce.repository.shipping.AddressModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("address")
public class AddressModelService {

    @Autowired
    AddressModelRepository addressModelRepository;

    public List<AddressModel> findAllAddressModel(){
        return addressModelRepository.findAll();
    }

    public AddressModel findAddressModelById(UUID id){
        return addressModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public AddressModel saveAddressModel(AddressModel addressModel){
        return addressModelRepository.save(addressModel);
    }

    @Transactional
    public void deleteAddressModel(UUID id){
        addressModelRepository.deleteById(id);
    }
}
