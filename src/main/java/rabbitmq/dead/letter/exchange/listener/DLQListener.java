package rabbitmq.dead.letter.exchange.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import rabbitmq.dead.letter.exchange.config.bind.RabbitConsumer;
import rabbitmq.dead.letter.exchange.model.MessageEvent;

import java.util.List;
import java.util.Map;

import static rabbitmq.dead.letter.exchange.config.MessagesNamespaces.DLQ;

@RabbitConsumer
class DLQListener {

    @RabbitListener(queues = DLQ)
    public void onFail(MessageEvent message, @Header("x-death") Map<String, List<?>> xdeath) {
        System.out.println("Fail message :" + message
         + "with information : " + xdeath);
    }
}
