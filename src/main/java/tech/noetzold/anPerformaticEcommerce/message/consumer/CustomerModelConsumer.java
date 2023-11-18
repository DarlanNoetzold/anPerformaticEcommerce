package tech.noetzold.anPerformaticEcommerce.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;
import tech.noetzold.anPerformaticEcommerce.service.CustomerModelService;

@Component
public class CustomerModelConsumer {

    @Autowired
    CustomerModelService customerModelService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.CUSTOMER_QUEUE)
    public void consumerCustomerModel(String message) throws JsonProcessingException {
        CustomerModel customerModel = new ObjectMapper().readValue(message, CustomerModel.class);
        try {
            customerModelService.saveCustomerModel(customerModel);
            logger.info("Consume customerModel - " + customerModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for customerModel - " + customerModel.toString(), ex);
        }

    }
    
}
