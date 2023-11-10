package tech.noetzold.anPerformaticEcommerce.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.CommerceItem;
import tech.noetzold.anPerformaticEcommerce.service.CommerceItemService;

@Component
public class CommerceItemConsumer {

    @Autowired
    CommerceItemService commerceItemService;

    private static final Logger logger = LoggerFactory.getLogger(CommerceItemConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.COMMERCE_ITEM_QUEUE)
    private void consumerCommerceItem(String message) throws JsonProcessingException {
        CommerceItem commerceItem = new ObjectMapper().readValue(message, CommerceItem.class);
        try {
            commerceItemService.saveCommerceItem(commerceItem);
            logger.info("Consume commerceItem - " + commerceItem.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for commerceItem - " + commerceItem.toString(), ex);
        }

    }
    
}
