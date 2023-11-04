package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PaypalModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.PaypalModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PaypalModelService {

    @Autowired
    PaypalModelRepository paypalModelRepository;

    public List<PaypalModel> findAllPaypalModel(){
        return paypalModelRepository.findAll();
    }

    public PaypalModel findPaypalModelById(UUID id){
        return paypalModelRepository.findById(id).orElse(null);
    }

    public PaypalModel savePaypalModel(PaypalModel paypalModel){
        return paypalModelRepository.save(paypalModel);
    }

    public void deletePaypalModel(UUID id){
        paypalModelRepository.deleteById(id);
    }
}
