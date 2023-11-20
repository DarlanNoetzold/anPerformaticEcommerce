package tech.noetzold.anPerformaticEcommerce.message.consumer.catalog;

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
import tech.noetzold.anPerformaticEcommerce.model.catalog.KeyWordModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.KeyWordModelService;

@Component
public class KeyWordModelConsumer {

    @Autowired
    KeyWordModelService keyWordModelService;

    private static final Logger logger = LoggerFactory.getLogger(KeyWordModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.KEY_WORD_QUEUE)
    public void consumerKeyWordModel(String message) throws JsonProcessingException {
        KeyWordModel keyWordModel = new ObjectMapper().readValue(message, KeyWordModel.class);
        try {
            keyWordModelService.saveKeyWordModel(keyWordModel);
            logger.info("Consume keyWordModel - " + keyWordModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for keyWordModel - " + keyWordModel.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
    
}
