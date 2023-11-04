package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PixModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.PixModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PixModelService {

    @Autowired
    PixModelRepository pixModelRepository;

    public List<PixModel> findAllPixModel(){
        return pixModelRepository.findAll();
    }

    public PixModel findPixModelById(UUID id){
        return pixModelRepository.findById(id).orElse(null);
    }

    public PixModel savePixModel(PixModel pixModel){
        return pixModelRepository.save(pixModel);
    }

    public void deletePixModel(UUID id){
        pixModelRepository.deleteById(id);
    }
}
