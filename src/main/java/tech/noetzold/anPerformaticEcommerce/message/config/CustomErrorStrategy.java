package tech.noetzold.anPerformaticEcommerce.message.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class CustomErrorStrategy implements FatalExceptionStrategy {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    private Queue deadLetterQueue(String queueName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "amq.direct");
        args.put("x-dead-letter-routing-key", queueName);
        return QueueBuilder.durable(queueName + ".dlq")
                .withArguments(args)
                .build();
    }

    private void setupQueueWithDLQ(String queueName) {
        Queue queue = new Queue(queueName, true, false, false);
        Queue dlq = deadLetterQueue(queueName);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareQueue(dlq);

        DirectExchange exchange = new DirectExchange("amq.direct");
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(queue.getName());
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
    }

    @Override
    public boolean isFatal(Throwable t) {
        if (t instanceof ListenerExecutionFailedException) {
            ListenerExecutionFailedException executionFailedException = (ListenerExecutionFailedException) t;
            Message failedMessage = executionFailedException.getFailedMessage();
            String queueName = failedMessage.getMessageProperties().getConsumerQueue();

            rabbitTemplate.convertAndSend(queueName + ".dlq", failedMessage.getBody());
        }
        return true;
    }
}