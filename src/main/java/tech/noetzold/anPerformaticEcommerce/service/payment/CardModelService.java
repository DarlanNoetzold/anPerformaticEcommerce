package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.CardModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.CardModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CardModelService {

    @Autowired
    CardModelRepository cardModelRepository;

    public List<CardModel> findAllCardModel(){
        return cardModelRepository.findAll();
    }

    public CardModel findCardModelById(UUID id){
        return cardModelRepository.findById(id).orElse(null);
    }

    public CardModel saveCardModel(CardModel cardModel){
        return cardModelRepository.save(cardModel);
    }

    public void deleteCardModel(UUID id){
        cardModelRepository.deleteById(id);
    }
}
