package tech.noetzold.anPerformaticEcommerce.message.consumer.shipping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;
import tech.noetzold.anPerformaticEcommerce.service.shipping.ShippingModelService;

public class ShippingModelConsumer {

    @Autowired
    ShippingModelService shippingModelService;

    private static final Logger logger = LoggerFactory.getLogger(ShippingModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.SHIPPING_QUEUE)
    private void consumerShippingModel(String message) throws JsonProcessingException {
        ShippingModel shippingModel = new ObjectMapper().readValue(message, ShippingModel.class);
        try {
            shippingModelService.saveShippingModel(shippingModel);
            logger.info("Consume shippingModel - " + shippingModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for shippingModel - " + shippingModel.toString(), ex);
        }

    }
}
