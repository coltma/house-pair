package demo.service.impl;

import demo.model.HouseNotification;
import demo.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQServiceImpl implements MQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void produce(HouseNotification notification) {
        log.info(String.format("Before send: %s", notification.getEmailAddress()));
        this.rabbitTemplate.convertAndSend("direct_email_server", "q_email_servers",notification);
    }
}
