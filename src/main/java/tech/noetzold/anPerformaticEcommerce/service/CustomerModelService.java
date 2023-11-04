package tech.noetzold.anPerformaticEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;
import tech.noetzold.anPerformaticEcommerce.repository.CustomerModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerModelService {

    @Autowired
    CustomerModelRepository customerModelRepository;

    public List<CustomerModel> findAllCustomerModel(){
        return customerModelRepository.findAll();
    }

    public CustomerModel findCustomerModelById(UUID id){
        return customerModelRepository.findById(id).orElse(null);
    }

    public CustomerModel saveCustomerModel(CustomerModel customerModel){
        return customerModelRepository.save(customerModel);
    }

    public void deleteCustomerModel(UUID id){
        customerModelRepository.deleteById(id);
    }
}
