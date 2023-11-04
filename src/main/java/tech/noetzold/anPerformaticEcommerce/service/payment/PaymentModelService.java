package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.PaymentModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentModelService {

    @Autowired
    PaymentModelRepository paymentModelRepository;

    public List<PaymentModel> findAllPaymentModel(){
        return paymentModelRepository.findAll();
    }

    public PaymentModel findPaymentModelById(UUID id){
        return paymentModelRepository.findById(id).orElse(null);
    }

    public PaymentModel savePaymentModel(PaymentModel paymentModel){
        return paymentModelRepository.save(paymentModel);
    }

    public void deletePaymentModel(UUID id){
        paymentModelRepository.deleteById(id);
    }
}
