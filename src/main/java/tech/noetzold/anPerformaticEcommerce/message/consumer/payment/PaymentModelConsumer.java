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
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;
import tech.noetzold.anPerformaticEcommerce.service.payment.PaymentModelService;

@Component
public class PaymentModelConsumer {

    @Autowired
    PaymentModelService paymentModelService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.PAYMENT_QUEUE)
    private void consumerPaymentModel(String message) throws JsonProcessingException {
        PaymentModel paymentModel = new ObjectMapper().readValue(message, PaymentModel.class);
        try {
            paymentModelService.savePaymentModel(paymentModel);
            logger.info("Consume paymentModel - " + paymentModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for paymentModel - " + paymentModel.toString(), ex);
        }

    }
    
}
