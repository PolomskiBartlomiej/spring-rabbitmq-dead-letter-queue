package rabbitmq.dead.letter.exchange.config;

public final class MessagesNamespaces {

    public static final String MESSAGE_EXCHANGE = "message.message";
    public static final String DLQ = "messages.message";
    public static final String DEAD_LETTER_QUEUE = DLQ + ".dlq";
    public static final String DEAD_LETTER_EXCHANGE = MESSAGE_EXCHANGE + ".dlq";

}
