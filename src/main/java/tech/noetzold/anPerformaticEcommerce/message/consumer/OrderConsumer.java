package tech.noetzold.anPerformaticEcommerce.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.anPerformaticEcommerce.client.PaymentClient;
import tech.noetzold.anPerformaticEcommerce.client.ShippingClient;
import tech.noetzold.anPerformaticEcommerce.controller.OrderController;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.OrderModel;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;
import tech.noetzold.anPerformaticEcommerce.service.LoginService;
import tech.noetzold.anPerformaticEcommerce.service.OrderModelService;

import java.util.List;

@Component
public class OrderConsumer {

    @Autowired
    OrderModelService orderModelService;

    @Autowired
    ShippingClient shippingClient;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.ORDER_QUEUE)
    public void consumerOrder(String message) throws JsonProcessingException {
        OrderModel order = new ObjectMapper().readValue(message, OrderModel.class);
        try {
            sendShippingModel(order.getShippingModels());
            sendPaymentModel(order.getPaymentModels());
            orderModelService.saveOrder(order);
            logger.info("Consume order - " + order.toString());
        }catch (Exception ex){
            logger.error("Error to consume cerate message for order - " + order.toString(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }

    public void sendShippingModel(List<ShippingModel> shippingModels) throws JSONException {
        for (ShippingModel shippingModel: shippingModels) {
            shippingClient.saveShipping(loginService.getToken(),shippingModel);
        }
    }

    public void sendPaymentModel(List<PaymentModel> paymentModels) throws JSONException {
        for (PaymentModel paymentModel: paymentModels) {
            paymentClient.savePayment(loginService.getToken(), paymentModel);
        }
    }

}
