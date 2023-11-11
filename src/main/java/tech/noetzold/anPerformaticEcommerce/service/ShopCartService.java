package tech.noetzold.anPerformaticEcommerce.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.ShopCart;
import tech.noetzold.anPerformaticEcommerce.repository.ShopCartRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("shopcart")
public class ShopCartService {

    @Autowired
    ShopCartRepository shopCartRepository;

    @Transactional
    public List<ShopCart> findAllShopCart(){
        return shopCartRepository.findAll();
    }

    @Transactional
    public ShopCart findShopCartById(UUID id){
        return shopCartRepository.findById(id).orElse(null);
    }

    public ShopCart updateShopCart(UUID id, ShopCart shopCart){
        shopCart.setShopCartId(id);
        return shopCartRepository.save(shopCart);
    }

    @Transactional
    public ShopCart saveShopCart(ShopCart shopCart){
        return shopCartRepository.save(shopCart);
    }

    @Transactional
    public void deleteShopCart(UUID id){
        shopCartRepository.deleteById(id);
    }
}
