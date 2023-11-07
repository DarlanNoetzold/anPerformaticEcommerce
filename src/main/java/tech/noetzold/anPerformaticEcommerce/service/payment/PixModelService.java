package tech.noetzold.anPerformaticEcommerce.service.payment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PixModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.PixModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("pix")
public class PixModelService {

    @Autowired
    PixModelRepository pixModelRepository;

    @Transactional
    public List<PixModel> findAllPixModel(){
        return pixModelRepository.findAll();
    }

    @Transactional
    public PixModel findPixModelById(UUID id){
        return pixModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public PixModel savePixModel(PixModel pixModel){
        return pixModelRepository.save(pixModel);
    }

    @Transactional
    public void deletePixModel(UUID id){
        pixModelRepository.deleteById(id);
    }
}
