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
import tech.noetzold.anPerformaticEcommerce.client.PaymentClient;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;
import tech.noetzold.anPerformaticEcommerce.service.LoginService;
import tech.noetzold.anPerformaticEcommerce.service.payment.PaymentModelService;

@Component
public class PaymentModelConsumer {

    @Autowired
    PaymentModelService paymentModelService;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.PAYMENT_QUEUE)
    public void consumerPaymentModel(String message) throws JsonProcessingException {
        PaymentModel paymentModel = new ObjectMapper().readValue(message, PaymentModel.class);
        try {
            paymentClient.savePayment(loginService.getToken(), paymentModel);
            paymentModelService.savePaymentModel(paymentModel);
            logger.info("Consume paymentModel - " + paymentModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for paymentModel - " + paymentModel.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
    
}
