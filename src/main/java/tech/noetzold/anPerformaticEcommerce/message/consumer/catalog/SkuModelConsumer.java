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
import tech.noetzold.anPerformaticEcommerce.model.catalog.SkuModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.SkuModelService;

@Component
public class SkuModelConsumer {

    @Autowired
    SkuModelService skuModelService;

    private static final Logger logger = LoggerFactory.getLogger(SkuModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.SKU_QUEUE)
    public void consumerSkuModel(String message) throws JsonProcessingException {
        SkuModel skuModel = new ObjectMapper().readValue(message, SkuModel.class);
        try {
            skuModelService.saveSkuModel(skuModel);
            logger.info("Consume skuModel - " + skuModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for skuModel - " + skuModel.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }

}
