package tech.noetzold.anPerformaticEcommerce.message.consumer.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PaypalModel;
import tech.noetzold.anPerformaticEcommerce.service.payment.PaypalModelService;

@Component
public class PaypalModelConsumer {

    @Autowired
    PaypalModelService paypalModelService;

    private static final Logger logger = LoggerFactory.getLogger(PaypalModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.PAYPAL_QUEUE)
    public void consumerPaypalModel(String message) throws JsonProcessingException {
        PaypalModel paypalModel = new ObjectMapper().readValue(message, PaypalModel.class);
        try {
            paypalModelService.savePaypalModel(paypalModel);
            logger.info("Consume paypalModel - " + paypalModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for paypalModel - " + paypalModel.toString(), ex);
        }

    }
    
}
