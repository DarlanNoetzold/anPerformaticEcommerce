package tech.noetzold.anPerformaticEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.ShopCart;
import tech.noetzold.anPerformaticEcommerce.repository.ShopCartRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ShopCartService {

    @Autowired
    ShopCartRepository shopCartRepository;

    public List<ShopCart> findAllShopCart(){
        return shopCartRepository.findAll();
    }

    public ShopCart findShopCartById(UUID id){
        return shopCartRepository.findById(id).orElse(null);
    }

    public ShopCart saveShopCart(ShopCart shopCart){
        return shopCartRepository.save(shopCart);
    }

    public void deleteShopCart(UUID id){
        shopCartRepository.deleteById(id);
    }
}
