package tech.noetzold.anPerformaticEcommerce.message.consumer.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PixModel;
import tech.noetzold.anPerformaticEcommerce.service.payment.PixModelService;

public class PixModelConsumer {

    @Autowired
    PixModelService pixModelService;

    private static final Logger logger = LoggerFactory.getLogger(PixModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.PIX_QUEUE)
    private void consumerPixModel(String message) throws JsonProcessingException {
        PixModel pixModel = new ObjectMapper().readValue(message, PixModel.class);
        try {
            pixModelService.savePixModel(pixModel);
            logger.info("Consume pixModel - " + pixModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for pixModel - " + pixModel.toString(), ex);
        }

    }
    
}
