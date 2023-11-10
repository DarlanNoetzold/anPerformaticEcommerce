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
import tech.noetzold.anPerformaticEcommerce.model.catalog.MediaModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.MediaModelService;

@Component
public class MediaModelConsumer {

    @Autowired
    MediaModelService mediaModelService;

    private static final Logger logger = LoggerFactory.getLogger(MediaModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.MEDIA_QUEUE)
    private void consumerMediaModel(String message) throws JsonProcessingException {
        MediaModel mediaModel = new ObjectMapper().readValue(message, MediaModel.class);
        try {
            mediaModelService.saveMediaModel(mediaModel);
            logger.info("Consume mediaModel - " + mediaModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for mediaModel - " + mediaModel.toString(), ex);
        }

    }
    
}
