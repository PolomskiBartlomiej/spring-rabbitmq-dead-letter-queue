package rabbitmq.dead.letter.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqDeadLetterExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDeadLetterExchangeApplication.class, args);
    }
}
