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
import tech.noetzold.anPerformaticEcommerce.model.payment.InvoiceModel;
import tech.noetzold.anPerformaticEcommerce.service.payment.InvoiceModelService;

@Component
public class InvoiceModelConsumer {

    @Autowired
    InvoiceModelService invoiceModelService;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.INVOICE_QUEUE)
    public void consumerInvoiceModel(String message) throws JsonProcessingException {
        InvoiceModel invoiceModel = new ObjectMapper().readValue(message, InvoiceModel.class);
        try {
            invoiceModelService.saveInvoiceModel(invoiceModel);
            logger.info("Consume invoiceModel - " + invoiceModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for invoiceModel - " + invoiceModel.toString(), ex);
        }

    }
    
}
