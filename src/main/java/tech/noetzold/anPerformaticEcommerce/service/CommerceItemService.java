package tech.noetzold.anPerformaticEcommerce.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.CommerceItem;
import tech.noetzold.anPerformaticEcommerce.repository.CommerceItemRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("commerceitem")
public class CommerceItemService {

    @Autowired
    CommerceItemRepository commerceItemRepository;

    @Transactional
    public List<CommerceItem> findAllCommerceItem(){
        return commerceItemRepository.findAll();
    }

    @Transactional
    public CommerceItem findCommerceItemById(UUID id){
        return commerceItemRepository.findById(id).orElse(null);
    }

    @Transactional
    public CommerceItem saveCommerceItem(CommerceItem commerceItem){
        return commerceItemRepository.save(commerceItem);
    }

    @Transactional
    public void deleteCommerceItem(UUID id){
        commerceItemRepository.deleteById(id);
    }
}
