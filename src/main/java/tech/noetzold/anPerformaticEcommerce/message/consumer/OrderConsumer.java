package tech.noetzold.anPerformaticEcommerce.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.controller.OrderController;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.Order;
import tech.noetzold.anPerformaticEcommerce.service.OrderService;

@Component
public class OrderConsumer {

    @Autowired
    OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.ORDER_QUEUE)
    private void consumerOrder(String message) throws JsonProcessingException {
        Order order = new ObjectMapper().readValue(message, Order.class);
        try {
            orderService.saveOrder(order);
            logger.info("Consume order - " + order.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for order - " + order.toString(), ex);
        }

    }
}
