package tech.noetzold.anPerformaticEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.ShopCart;
import tech.noetzold.anPerformaticEcommerce.service.ShopCartService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/shopcart")
public class ShopCartController {

    @Autowired
    ShopCartService shopCartService;

    private static final Logger logger = LoggerFactory.getLogger(ShopCartController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<ShopCart>> getAll() {
        Collection<ShopCart> shopCarts = shopCartService.findAllShopCart();
        if (shopCarts.isEmpty()) {
            logger.warn("No shopCart register");
            return new ResponseEntity<Collection<ShopCart>>(HttpStatus.NO_CONTENT);
        }

        logger.info("shopCarts "+shopCarts.size()+" returned");
        return new ResponseEntity<Collection<ShopCart>>(shopCarts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ShopCart> getShopCartById(@PathVariable("id") String id) {
        ShopCart shopCart = shopCartService.findShopCartById(UUID.fromString(id));
        if (shopCart == null) {
            logger.warn("shopCart not found");
            return new ResponseEntity<ShopCart>(HttpStatus.NOT_FOUND);
        }
        logger.info("shopCart "+shopCart.getShopCartId()+" returned");
        return new ResponseEntity<ShopCart>(shopCart, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ShopCart> save(@RequestBody ShopCart shopCart) {
        if (shopCart == null) {
            logger.warn("shopCart is null");
            return new ResponseEntity<ShopCart>(HttpStatus.BAD_REQUEST);
        }

        shopCart = shopCartService.saveShopCart(shopCart);
        logger.info("Create shopCart: " + shopCart);
        return new ResponseEntity<ShopCart>(shopCart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            shopCartService.deleteShopCart(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove shopCart", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove shopCart: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
