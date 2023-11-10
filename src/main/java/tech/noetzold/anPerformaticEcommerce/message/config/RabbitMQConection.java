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

        Queue promotionQueue = this.fila(RabbitmqQueues.PROMOTION_QUEUE);
        DirectExchange promotionExchange = this.directExchange();
        Binding promotionBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(promotionQueue);
        this.amqpAdmin.declareExchange(promotionExchange);
        this.amqpAdmin.declareBinding(promotionBiding);

        Queue couponQueue = this.fila(RabbitmqQueues.COUPON_QUEUE);
        DirectExchange couponExchange = this.directExchange();
        Binding couponBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(couponQueue);
        this.amqpAdmin.declareExchange(couponExchange);
        this.amqpAdmin.declareBinding(couponBiding);

        Queue pixModelQueue = this.fila(RabbitmqQueues.PIX_QUEUE);
        DirectExchange pixModelExchange = this.directExchange();
        Binding pixModelBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(pixModelQueue);
        this.amqpAdmin.declareExchange(pixModelExchange);
        this.amqpAdmin.declareBinding(pixModelBiding);

        Queue paypalModelQueue = this.fila(RabbitmqQueues.PAYPAL_QUEUE);
        DirectExchange paypalModelExchange = this.directExchange();
        Binding paypalModelBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(paypalModelQueue);
        this.amqpAdmin.declareExchange(paypalModelExchange);
        this.amqpAdmin.declareBinding(paypalModelBiding);

        Queue paymentModelQueue = this.fila(RabbitmqQueues.PAYMENT_QUEUE);
        DirectExchange paymentModelExchange = this.directExchange();
        Binding paymentModelBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(paymentModelQueue);
        this.amqpAdmin.declareExchange(paymentModelExchange);
        this.amqpAdmin.declareBinding(paymentModelBiding);

        Queue invoiceModelQueue = this.fila(RabbitmqQueues.INVOICE_QUEUE);
        DirectExchange invoiceModelExchange = this.directExchange();
        Binding invoiceModelBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(invoiceModelQueue);
        this.amqpAdmin.declareExchange(invoiceModelExchange);
        this.amqpAdmin.declareBinding(invoiceModelBiding);

        Queue cardModelQueue = this.fila(RabbitmqQueues.CARD_QUEUE);
        DirectExchange cardModelExchange = this.directExchange();
        Binding cardModelBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(cardModelQueue);
        this.amqpAdmin.declareExchange(cardModelExchange);
        this.amqpAdmin.declareBinding(cardModelBiding);

        Queue boletoModelQueue = this.fila(RabbitmqQueues.BOLETO_QUEUE);
        DirectExchange boletoModelExchange = this.directExchange();
        Binding boletoModelBiding = this.binding(cartQueue, cartExchange);

        this.amqpAdmin.declareQueue(boletoModelQueue);
        this.amqpAdmin.declareExchange(boletoModelExchange);
        this.amqpAdmin.declareBinding(boletoModelBiding);
    }
}
