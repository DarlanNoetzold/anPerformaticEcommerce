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
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.promotion.CouponModel;
import tech.noetzold.anPerformaticEcommerce.service.promotion.CouponModelService;

@Component
public class CouponModelConsumer {

    @Autowired
    CouponModelService promotionModelService;

    private static final Logger logger = LoggerFactory.getLogger(CouponModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.COUPON_QUEUE)
    public void consumerCouponModel(String message) throws JsonProcessingException {
        CouponModel promotionModel = new ObjectMapper().readValue(message, CouponModel.class);
        try {
            promotionModelService.saveCouponModel(promotionModel);
            logger.info("Consume promotionModel - " + promotionModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for promotionModel - " + promotionModel.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
    
}
