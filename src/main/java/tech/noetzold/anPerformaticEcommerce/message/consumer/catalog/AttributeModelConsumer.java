package tech.noetzold.anPerformaticEcommerce.message.consumer.catalog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.catalog.AttributeModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.AttributeModelService;

@Component
public class AttributeModelConsumer {

    @Autowired
    AttributeModelService attributeModelService;

    private static final Logger logger = LoggerFactory.getLogger(AttributeModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.ATTRIBUTE_QUEUE)
    private void consumerAttributeModel(String message) throws JsonProcessingException {
        AttributeModel attributeModel = new ObjectMapper().readValue(message, AttributeModel.class);
        try {
            attributeModelService.saveAttributeModel(attributeModel);
            logger.info("Consume attributeModel - " + attributeModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for attributeModel - " + attributeModel.toString(), ex);
        }

    }
    
}
