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
    
    
# project description

  
 
    



