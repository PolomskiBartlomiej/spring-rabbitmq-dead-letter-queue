package rabbitmq.dead.letter.exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rabbitmq.dead.letter.exchange.config.bind.DeadLetter;

import static rabbitmq.dead.letter.exchange.config.MessagesNamespaces.DEAD_LETTER_EXCHANGE;
import static rabbitmq.dead.letter.exchange.config.MessagesNamespaces.DEAD_LETTER_QUEUE;


@Configuration
class RabbitDLQConfiguration {

    @Bean
    @DeadLetter Exchange
    deadLetterExchange() {
       return ExchangeBuilder.directExchange(DEAD_LETTER_EXCHANGE).build();
    }

    @Bean
    @DeadLetter Queue
    deadQueue() {
        return QueueBuilder
                .durable(DEAD_LETTER_QUEUE)
                .build();
    }

    @Bean Binding
    deadLetterBind(@DeadLetter Queue queue, @DeadLetter Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE).noargs();
    }
}
