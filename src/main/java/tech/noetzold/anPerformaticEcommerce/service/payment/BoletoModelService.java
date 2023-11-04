package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.BoletoModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.BoletoModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BoletoModelService {

    @Autowired
    BoletoModelRepository boletoModelRepository;

    public List<BoletoModel> findAllBoletoModel(){
        return boletoModelRepository.findAll();
    }

    public BoletoModel findBoletoModelById(UUID id){
        return boletoModelRepository.findById(id).orElse(null);
    }

    public BoletoModel saveBoletoModel(BoletoModel boletoModel){
        return boletoModelRepository.save(boletoModel);
    }

    public void deleteBoletoModel(UUID id){
        boletoModelRepository.deleteById(id);
    }
}
