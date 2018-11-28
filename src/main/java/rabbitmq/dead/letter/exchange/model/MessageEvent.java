package rabbitmq.dead.letter.exchange.model;

import lombok.Value;

@Value
public class MessageEvent {
    String text;
}
