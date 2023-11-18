package tech.noetzold.anPerformaticEcommerce.message.consumer.shipping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.shipping.AddressModel;
import tech.noetzold.anPerformaticEcommerce.service.shipping.AddressModelService;

@Component
public class AddressModelConsumer {

    @Autowired
    AddressModelService addressModelService;

    private static final Logger logger = LoggerFactory.getLogger(AddressModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.ADDRESS_QUEUE)
    public void consumerAddressModel(String message) throws JsonProcessingException {
        AddressModel addressModel = new ObjectMapper().readValue(message, AddressModel.class);
        try {
            addressModelService.saveAddressModel(addressModel);
            logger.info("Consume addressModel - " + addressModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for addressModel - " + addressModel.toString(), ex);
        }

    }
}
