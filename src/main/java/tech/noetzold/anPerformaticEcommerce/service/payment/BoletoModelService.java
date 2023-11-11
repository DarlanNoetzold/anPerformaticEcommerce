package tech.noetzold.anPerformaticEcommerce.service.payment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.BoletoModel;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.BoletoModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.BoletoModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("boleto")
public class BoletoModelService {

    @Autowired
    BoletoModelRepository boletoModelRepository;

    @Transactional
    public List<BoletoModel> findAllBoletoModel(){
        return boletoModelRepository.findAll();
    }

    @Transactional
    public BoletoModel findBoletoModelById(UUID id){
        return boletoModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public BoletoModel updateBoletoModel(UUID id, BoletoModel boletoModel){
        boletoModel.setBoletoId(id);
        return boletoModelRepository.save(boletoModel);
    }
    
    @Transactional
    public BoletoModel saveBoletoModel(BoletoModel boletoModel){
        return boletoModelRepository.save(boletoModel);
    }

    @Transactional
    public void deleteBoletoModel(UUID id){
        boletoModelRepository.deleteById(id);
    }
}
