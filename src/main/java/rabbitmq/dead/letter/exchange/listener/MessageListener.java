package rabbitmq.dead.letter.exchange.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import rabbitmq.dead.letter.exchange.config.bind.RabbitConsumer;
import rabbitmq.dead.letter.exchange.model.MessageEvent;

import static rabbitmq.dead.letter.exchange.config.MessagesNamespaces.MESSAGE_QUEUE;

@RabbitConsumer
@RequiredArgsConstructor
class MessageListener {

    @RabbitListener(queues = MESSAGE_QUEUE)
    public void onMessage(MessageEvent message) {
        System.out.println(message);
        throw  new RuntimeException("occurs error!");
    }
}
