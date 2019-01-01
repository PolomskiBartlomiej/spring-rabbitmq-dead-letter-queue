# spring-rabbitmq-dead-letter-queue
Exploring spring rabbitmq dead letter queue

Reference: https://www.rabbitmq.com/dlx.html

# dead letter

Dead letter queue is queue which collects the message whose cannot be consumed from normal queue,
to provide this mechanism events must occurs:
 1. The message is rejected (basic.reject or basic.nack) with requeue=false,
 1. The TTL for the message expires; or
 1. The queue length limit is exceeded.
 
 Dead letter exchanges (DLXs) are normal exchanges. They can be any of the usual types and are declared as usual.
 
# configuration dead letter
    For any given queue, a DLX can be defined by clients using the queue's arguments,
    or in the server using policies. In the case where both policy and arguments specify a DLX,
    the one specified in arguments overrules the one specified in polic
 
**Configuration using argument :**
 
  To set dead letter exchange:
    
    To set the dead letter exchange for a queue,
    set the x-dead-letter-exchange argument to the name of the exchange
    args.put("x-dead-letter-exchange", "some.exchange.name");
 
 You may also specify a routing key to be used when dead-lettering messages:
     
    args.put("x-dead-letter-routing-key", "some-routing-key");
    
**Configuration using policy :**

    To specify a DLX using policy, add the key "dead-letter-exchange" to a policy definition. For example:	
    rabbitmqctl set_policy DLX ".*" '{"dead-letter-exchange":"my-dlx"}' --apply-to queues
    rabbitmqctl (Windows)	set_policy DLX ".*" "{""dead-letter-exchange"":""my-dlx""}" --apply-to queues 
    This applies the DLX "my-dlx" to all queues.
    
    Similarly, an explicit routing key can be specified by adding the key "dead-letter-routing-key" to the policy.
    Policies can also be defined using the management plugin, see the policy documentation for more details.
    
# x-death header

 Dlq contains x-death headers contains information about original message configuration
   
    The dead-lettering process adds an array to the header of each dead-lettered message named  x-death.
    This array contains an entry for each dead lettering event, identified by a pair of {queue, reason}.
    
    Each such entry is a table that consists of several fields:

    queue - the name of the queue the message was in before it was dead-lettered,
    reason - see below,
    time - the date and time the message was dead lettered as a 64-bit AMQP format timestamp,
    exchange - the exchange the message was published to (note that this will be a dead letter exchange if the message is dead lettered multiple times),
    routing-keys - the routing keys (including CC keys but excluding BCC ones) the message was published with,
    count - how many times this message was dead-lettered in this queue for this reason, and
    original-expiration (if the message was dead-letterered due to per-message TTL) - the original expiration property of the message. 
    
    The expiration property is removed from the message on dead-lettering in order to prevent it from expiring again in any queues it is  routed to.
    
    New entries are prepended to the beginning of the x-death array.
    
    In case x-death already contains an entry with the same queue and dead lettering reason,
    its count field will be incremented and it will be moved to the beginning of the array.
     
    The reason is a name describing why the message was dead-lettered and is one of the following:

    rejected - the message was rejected with requeue=false,
    expired - the TTL of the message expired; or
    maxlen - the maximum allowed queue length was exceeded.
  
    Note that the array is sorted most-recent-first, so the most recent dead-lettering will be recorded in the first entry.
 
 

# project description
 
 Project show how to configure Dead letter mechanism in Spring Boot App
 
 To configure it we must provide :
 1. Dead Letter Queue , Dead Letter Exchange and binds it together
 2. Configure normal queue to sending message if error occur
 3. Configure Ampq to not requeue failed message
 
**1. Dead Letter Queue , Dead Letter Exchange**
 To configure DLX queue and DLX exchange we must create normal configuration 
 For Example
    
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
    
**2. Configure normal queue to sending message if error occur**
To configure queue to point to the DLX we must add arguments x-dead-letter-exchange and x-dead-letter-routing-key to bean creation:

    @Configuration
    public class RabbitMessageConfiguration {

    @Bean
    @Message Queue
    smsQueue() {
        return QueueBuilder
                .durable(MESSAGE_QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE)
                .build();
    }
    ...
    }
    
**3. Configure Ampq to not requeue failed message**
To provide mechanism to stop requeu message when failed we can :
 1. Set in properties `spring.rabbitmq.listener.simple.default-requeue-rejected=false`
 2. catch error and thrown `AmqpRejectAndDontRequeueException()`
 3. Configure `SimpleMessageListenerContainer.class`
 







 
 
  
 
    



