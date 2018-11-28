package rabbitmq.dead.letter.exchange.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import rabbitmq.dead.letter.exchange.model.MessageEvent;

@RequiredArgsConstructor
public class MessagePublisher {

    private final RabbitTemplate template;
    private final Exchange exchange;

    public void sendMessage(MessageEvent event) {
        template.convertAndSend(exchange.getName(), event);
    }
}
