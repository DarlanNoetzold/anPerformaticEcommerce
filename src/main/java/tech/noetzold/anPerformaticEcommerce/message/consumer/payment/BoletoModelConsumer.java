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
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.BoletoModel;
import tech.noetzold.anPerformaticEcommerce.service.payment.BoletoModelService;

@Component
public class BoletoModelConsumer {

    @Autowired
    BoletoModelService boletoModelService;

    private static final Logger logger = LoggerFactory.getLogger(BoletoModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.BOLETO_QUEUE)
    public void consumerBoletoModel(String message) throws JsonProcessingException {
        BoletoModel boletoModel = new ObjectMapper().readValue(message, BoletoModel.class);
        try {
            boletoModelService.saveBoletoModel(boletoModel);
            logger.info("Consume boletoModel - " + boletoModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for boletoModel - " + boletoModel.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
}
