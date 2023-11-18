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
import tech.noetzold.anPerformaticEcommerce.model.catalog.CategoryModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.CategoryModelService;

@Component
public class CategoryModelConsumer {

    @Autowired
    CategoryModelService categoryModelService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryModelConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.CATEGORY_QUEUE)
    public void consumerCategoryModel(String message) throws JsonProcessingException {
        CategoryModel categoryModel = new ObjectMapper().readValue(message, CategoryModel.class);
        try {
            categoryModelService.saveCategoryModel(categoryModel);
            logger.info("Consume categoryModel - " + categoryModel.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for categoryModel - " + categoryModel.toString(), ex);
        }

    }
    
}
