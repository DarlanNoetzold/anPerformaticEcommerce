package tech.noetzold.anPerformaticEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.CommerceItem;
import tech.noetzold.anPerformaticEcommerce.repository.CommerceItemRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CommerceItemService {

    @Autowired
    CommerceItemRepository commerceItemRepository;

    public List<CommerceItem> findAllCommerceItem(){
        return commerceItemRepository.findAll();
    }

    public CommerceItem findCommerceItemById(UUID id){
        return commerceItemRepository.findById(id).orElse(null);
    }

    public CommerceItem saveCommerceItem(CommerceItem commerceItem){
        return commerceItemRepository.save(commerceItem);
    }

    public void deleteCommerceItem(UUID id){
        commerceItemRepository.deleteById(id);
    }
}
