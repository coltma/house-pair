package demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.HouseNotification;
import demo.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableCircuitBreaker
public class MQServiceImpl implements MQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @HystrixCommand(fallbackMethod = "produceNotificationFallBack")
    @Override
    public void produce(HouseNotification notification) throws JsonProcessingException {
        log.info(String.format("Before send: %s", notification.getEmailAddress()));
        this.rabbitTemplate.convertAndSend("direct_email_server",
                "q_email_servers",
                 objectMapper.writeValueAsString(notification));
    }

    public void produceNotificationFallBack(HouseNotification notification) {
        log.error("Hystrix Fallback Method. Unable to send message for notification.");
    }
}
