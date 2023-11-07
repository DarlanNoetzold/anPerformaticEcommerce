package tech.noetzold.anPerformaticEcommerce.service.payment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.PaymentModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("payment")
public class PaymentModelService {

    @Autowired
    PaymentModelRepository paymentModelRepository;

    @Transactional
    public List<PaymentModel> findAllPaymentModel(){
        return paymentModelRepository.findAll();
    }

    @Transactional
    public PaymentModel findPaymentModelById(UUID id){
        return paymentModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public PaymentModel savePaymentModel(PaymentModel paymentModel){
        return paymentModelRepository.save(paymentModel);
    }

    @Transactional
    public void deletePaymentModel(UUID id){
        paymentModelRepository.deleteById(id);
    }
}
