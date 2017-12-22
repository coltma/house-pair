package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.HouseData;
import demo.model.HouseNotification;
import demo.model.Subscription;
import demo.service.HouseNotificationService;
import demo.service.HouseSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.IOException;
import java.util.List;

@EnableBinding(Sink.class)
@MessageEndpoint
@Slf4j
public class HouseSubscriptionSink {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HouseNotificationService notificationService;

    @ServiceActivator(inputChannel = Sink.INPUT)

    public void consumeUpdatedHouses(HouseData input) throws IOException {
        log.info(String.format("Received Message %d", input.getPostId()));
        List<HouseNotification> list = this.notificationService.prepareNotifications(input);
//        HouseData houseData = this.objectMapper.readValue(input, HouseData.class);
    }

}
