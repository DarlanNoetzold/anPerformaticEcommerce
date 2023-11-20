package tech.noetzold.anPerformaticEcommerce.message.consumer.payment;

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
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.CardModel;
import tech.noetzold.anPerformaticEcommerce.service.payment.CardModelService;

@Component
public class CardModelConsumer {

    @Autowired
    CardModelService cardModelService;

    private static final Logger logger = LoggerFactory.getLogger(CardModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.CARD_QUEUE)
    public void consumerCardModel(String message) throws JsonProcessingException {
        CardModel cardModel = new ObjectMapper().readValue(message, CardModel.class);
        try {
            cardModelService.saveCardModel(cardModel);
            logger.info("Consume cardModel - " + cardModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for cardModel - " + cardModel.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
    
}
