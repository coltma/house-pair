package demo.config;

import demo.service.HouseDataConsumingService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${spring.queuenames.name}")
    private String queueName;

    @Autowired
    private HouseDataConsumingService service;

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("direct_county");
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(this.service, "consumeMessage");
    }

//    @Bean
//    public MongoOperations myBean() throws UnknownHostException {
//        // instantiate, configure and return bean ...
//        return new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));
//
//    }
}
