package tech.noetzold.anPerformaticEcommerce.message.consumer.promotion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.client.PromotionClient;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.promotion.PromotionModel;
import tech.noetzold.anPerformaticEcommerce.service.LoginService;
import tech.noetzold.anPerformaticEcommerce.service.promotion.PromotionModelService;

@Component
public class PromotionModelConsumer {

    @Autowired
    PromotionModelService promotionModelService;

    @Autowired
    PromotionClient promotionClient;

    @Autowired
    LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(PromotionModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.PROMOTION_QUEUE)
    public void consumerPromotionModel(String message) throws JsonProcessingException {
        PromotionModel promotionModel = new ObjectMapper().readValue(message, PromotionModel.class);
        try {
            promotionClient.savePromotion(loginService.getToken(), promotionModel);
            promotionModelService.savePromotionModel(promotionModel);
            logger.info("Consume promotionModel - " + promotionModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for promotionModel - " + promotionModel.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
    
}
