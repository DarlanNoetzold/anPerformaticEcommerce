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
import tech.noetzold.anPerformaticEcommerce.model.catalog.ProductModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.ProductModelService;

@Component
public class ProductModelConsumer {

    @Autowired
    ProductModelService productModelService;

    private static final Logger logger = LoggerFactory.getLogger(ProductModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.PRODUCT_QUEUE)
    private void consumerProductModel(String message) throws JsonProcessingException {
        ProductModel productModel = new ObjectMapper().readValue(message, ProductModel.class);
        try {
            productModelService.saveProductModel(productModel);
            logger.info("Consume productModel - " + productModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for productModel - " + productModel.toString(), ex);
        }

    }
    
}
