package tech.noetzold.anPerformaticEcommerce.service.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.shipping.AddressModel;
import tech.noetzold.anPerformaticEcommerce.repository.shipping.AddressModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AddressModelService {

    @Autowired
    AddressModelRepository addressModelRepository;

    public List<AddressModel> findAllAddressModel(){
        return addressModelRepository.findAll();
    }

    public AddressModel findAddressModelById(UUID id){
        return addressModelRepository.findById(id).orElse(null);
    }

    public AddressModel saveAddressModel(AddressModel addressModel){
        return addressModelRepository.save(addressModel);
    }

    public void deleteAddressModel(UUID id){
        addressModelRepository.deleteById(id);
    }
}
