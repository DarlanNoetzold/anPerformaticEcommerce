package tech.noetzold.JVMParamsCompare.message.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitMQConection {
    private static final String NAME_EXCHANGE = "amq.direct";

    private final AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue deadLetterQueue(String queueName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", NAME_EXCHANGE);
        args.put("x-dead-letter-routing-key", queueName);
        return QueueBuilder.durable(queueName + ".dlq")
                .withArguments(args)
                .build();
    }

    private Queue declareQueueWithDLQ(String queueName){
        Queue queue = new Queue(queueName, true, false, false);
        Queue dlq = deadLetterQueue(queueName);
        amqpAdmin.declareQueue(dlq);
        return queue;
    }

    private DirectExchange directExchange() {
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding declareBinding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
    }

    @PostConstruct
    private void setupQueues(){
        String[] queueNames = {
                RabbitmqQueues.SHOP_CART_QUEUE,
                RabbitmqQueues.ORDER_QUEUE,
                RabbitmqQueues.CUSTOMER_QUEUE,
                RabbitmqQueues.COMMERCE_ITEM_QUEUE,
                RabbitmqQueues.ADDRESS_QUEUE,
                RabbitmqQueues.SHIPPING_QUEUE,
                RabbitmqQueues.PROMOTION_QUEUE,
                RabbitmqQueues.COUPON_QUEUE,
                RabbitmqQueues.PIX_QUEUE,
                RabbitmqQueues.PAYPAL_QUEUE,
                RabbitmqQueues.PAYMENT_QUEUE,
                RabbitmqQueues.INVOICE_QUEUE,
                RabbitmqQueues.CARD_QUEUE,
                RabbitmqQueues.BOLETO_QUEUE,
                RabbitmqQueues.SKU_QUEUE,
                RabbitmqQueues.PRODUCT_QUEUE,
                RabbitmqQueues.MEDIA_QUEUE,
                RabbitmqQueues.KEY_WORD_QUEUE,
                RabbitmqQueues.CATEGORY_QUEUE,
                RabbitmqQueues.ATTRIBUTE_QUEUE
        };

        DirectExchange exchange = directExchange();

        for (String queueName : queueNames) {
            Queue queue = declareQueueWithDLQ(queueName);
            Binding binding = declareBinding(queue, exchange);

            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareBinding(binding);
        }
    }
}
