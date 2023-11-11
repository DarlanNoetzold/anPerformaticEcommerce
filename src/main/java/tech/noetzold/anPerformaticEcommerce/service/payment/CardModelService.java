package tech.noetzold.anPerformaticEcommerce.service.payment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.CardModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.CardModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("card")
public class CardModelService {

    @Autowired
    CardModelRepository cardModelRepository;

    @Transactional
    public List<CardModel> findAllCardModel(){
        return cardModelRepository.findAll();
    }

    @Transactional
    public CardModel findCardModelById(UUID id){
        return cardModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public CardModel updateCardModel(UUID id, CardModel cardModel){
        cardModel.setCardId(id);
        return cardModelRepository.save(cardModel);
    }
    
    @Transactional
    public CardModel saveCardModel(CardModel cardModel){
        return cardModelRepository.save(cardModel);
    }

    @Transactional
    public void deleteCardModel(UUID id){
        cardModelRepository.deleteById(id);
    }
}
