package tech.noetzold.anPerformaticEcommerce.message.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {
    private static final String NAME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String queueName){
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void increase(){
        Queue cartQueue = this.fila(RabbitmqQueues.SHOP_CART_QUEUE);
        DirectExchange cartExchange = this.directExchange();
        Binding cartBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(cartQueue);
        this.amqpAdmin.declareExchange(cartExchange);
        this.amqpAdmin.declareBinding(cartBiding);

        Queue orderQueue = this.fila(RabbitmqQueues.ORDER_QUEUE);
        DirectExchange orderExchange = this.directExchange();
        Binding orderBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(orderQueue);
        this.amqpAdmin.declareExchange(orderExchange);
        this.amqpAdmin.declareBinding(orderBiding);

        Queue customerQueue = this.fila(RabbitmqQueues.CUSTOMER_QUEUE);
        DirectExchange customerExchange = this.directExchange();
        Binding customerBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(customerQueue);
        this.amqpAdmin.declareExchange(customerExchange);
        this.amqpAdmin.declareBinding(customerBiding);

        Queue commerceItemQueue = this.fila(RabbitmqQueues.COMMERCE_ITEM_QUEUE);
        DirectExchange commerceItemExchange = this.directExchange();
        Binding commerceItemBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(commerceItemQueue);
        this.amqpAdmin.declareExchange(commerceItemExchange);
        this.amqpAdmin.declareBinding(commerceItemBiding);

        Queue addressQueue = this.fila(RabbitmqQueues.ADDRESS_QUEUE);
        DirectExchange addressExchange = this.directExchange();
        Binding addressBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(addressQueue);
        this.amqpAdmin.declareExchange(addressExchange);
        this.amqpAdmin.declareBinding(addressBiding);

        Queue shippingQueue = this.fila(RabbitmqQueues.SHIPPING_QUEUE);
        DirectExchange shippingExchange = this.directExchange();
        Binding shippingBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(shippingQueue);
        this.amqpAdmin.declareExchange(shippingExchange);
        this.amqpAdmin.declareBinding(shippingBiding);
    }
}
