package tech.noetzold.anPerformaticEcommerce.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.controller.ShopCartController;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.ShopCart;
import tech.noetzold.anPerformaticEcommerce.service.ShopCartService;

@Component
public class ShopCartConsumer {

    @Autowired
    ShopCartService shopCartService;

    private static final Logger logger = LoggerFactory.getLogger(ShopCartController.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.SHOP_CART_QUEUE)
    private void consumerShopCart(String message) throws JsonProcessingException {
        ShopCart shopCart = new ObjectMapper().readValue(message, ShopCart.class);
        try {
            shopCartService.saveShopCart(shopCart);
            logger.info("Consume shopCart - " + shopCart.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for shopCarta - " + shopCart.toString(), ex);
        }

    }
}
