package demo.receiver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.service.HouseDataService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

@Component
public class RabbitMQReceiver {

    private HouseDataService houseDataService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public RabbitMQReceiver(HouseDataService houseDataService) {
        this.houseDataService = houseDataService;
    }

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "q_san_francisco_county")
    public void receiveMessage(byte[] message) {
        String dataStr = "{}";
        try {
            dataStr = new String(message, "UTF-8");
            HouseData data = this.objectMapper.readValue(dataStr, HouseData.class);
            this.houseDataService.save(data);
            System.out.println(" [x] Received <" + data.getPost_id() +  ">");
            System.out.println(" [x] Received <" + data.getLocation().getType() +  ">");
            System.out.println(" [x] Received <" + data.getLocation().getCoordinates() +  ">");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        latch.getCount();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
