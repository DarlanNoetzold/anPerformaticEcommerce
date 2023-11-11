package tech.noetzold.anPerformaticEcommerce.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;
import tech.noetzold.anPerformaticEcommerce.repository.CustomerModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("customer")
public class CustomerModelService {

    @Autowired
    CustomerModelRepository customerModelRepository;

    @Transactional
    public List<CustomerModel> findAllCustomerModel(){
        return customerModelRepository.findAll();
    }

    @Transactional
    public CustomerModel findCustomerModelById(UUID id){
        return customerModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public CustomerModel updateCustomerModel(UUID id, CustomerModel customerModel){
        customerModel.setCustomerId(id);
        return customerModelRepository.save(customerModel);
    }
    
    @Transactional
    public CustomerModel saveCustomerModel(CustomerModel customerModel){
        return customerModelRepository.save(customerModel);
    }

    @Transactional
    public void deleteCustomerModel(UUID id){
        customerModelRepository.deleteById(id);
    }
}
