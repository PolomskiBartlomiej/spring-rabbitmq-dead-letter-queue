package rabbitmq.dead.letter.exchange;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class RabbitmqDeadLetterExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDeadLetterExchangeApplication.class, args);
    }
}
