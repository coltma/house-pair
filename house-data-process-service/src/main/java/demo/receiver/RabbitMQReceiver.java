package demo.receiver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.HouseData;
import demo.repository.HouseDataRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

@Component
public class RabbitMQReceiver {

//    private HouseDataRepository repository;

//    @Autowired
//    private ObjectMapper objectMapper;

//    @Autowired
//    public RabbitMQReceiver(HouseDataRepository repository) {
//        this.repository = repository;
//    }

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "q_los_angeles_county")
    public void receiveMessage(byte[] message) {
        String dataStr = "{}";
        try {
            dataStr = new String(message, "UTF-8");
//            HouseData data = this.objectMapper.readValue(dataStr, HouseData.class);
//            this.repository.save(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
//        } catch (JsonParseException e) {
            e.printStackTrace();
//        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" [x] Received <" + dataStr + ">");
        latch.getCount();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
