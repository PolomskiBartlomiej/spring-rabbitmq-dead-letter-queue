package rabbitmq.dead.letter.exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rabbitmq.dead.letter.exchange.config.bind.Message;
import rabbitmq.dead.letter.exchange.publisher.MessagePublisher;

import static rabbitmq.dead.letter.exchange.config.MessagesNamespaces.*;


@Configuration
public class RabbitMessageConfiguration {

    @Bean
    @Message Queue
    smsQueue() {
        return QueueBuilder
                .durable(DLQ)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE)
                .build();
    }

    @Bean
    @Message Exchange
    smsExchange() {
        return ExchangeBuilder.directExchange(MESSAGE_EXCHANGE).build();
    }

    @Bean Binding
    smsBinder(@Message Queue queue, @Message Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DLQ).noargs();
    }

    @Bean MessagePublisher
    smsPublisher(RabbitTemplate template, @Message Exchange exchange) {
        return new MessagePublisher(template, exchange);
    }
}
