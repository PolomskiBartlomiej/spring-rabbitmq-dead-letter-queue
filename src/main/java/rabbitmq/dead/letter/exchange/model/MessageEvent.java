package rabbitmq.dead.letter.exchange.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
public class MessageEvent {
    @JsonProperty("text")
    private final String text;

    @JsonCreator
    public MessageEvent(String text) {
        this.text = text;
    }
}
