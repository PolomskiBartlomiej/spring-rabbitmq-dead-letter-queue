package rabbitmq.dead.letter.exchange.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import rabbitmq.dead.letter.exchange.config.bind.RabbitConsumer;
import rabbitmq.dead.letter.exchange.model.MessageEvent;

import static rabbitmq.dead.letter.exchange.config.MessagesNamespaces.DLQ;

@RabbitConsumer
class MessageListener {

    @RabbitListener(queues = DLQ)
    public void onMessage(MessageEvent message) {
        System.out.println(message);
        throw  new RuntimeException("occurs error!");
    }
}
