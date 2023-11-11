package tech.noetzold.anPerformaticEcommerce.service.payment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PaypalModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.PaypalModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("paypal")
public class PaypalModelService {

    @Autowired
    PaypalModelRepository paypalModelRepository;

    @Transactional
    public List<PaypalModel> findAllPaypalModel(){
        return paypalModelRepository.findAll();
    }

    @Transactional
    public PaypalModel findPaypalModelById(UUID id){
        return paypalModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public PaypalModel updatePaypalModel(UUID id, PaypalModel paypalModel){
        paypalModel.setPaypalId(id);
        return paypalModelRepository.save(paypalModel);
    }
    
    @Transactional
    public PaypalModel savePaypalModel(PaypalModel paypalModel){
        return paypalModelRepository.save(paypalModel);
    }

    @Transactional
    public void deletePaypalModel(UUID id){
        paypalModelRepository.deleteById(id);
    }
}
