package demo.service.impl;

import com.mongodb.MongoClient;
import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.service.HouseDataProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Slf4j
@Service
@EnableBinding(Source.class)
public class HouseDataProcessServiceImpl implements HouseDataProcessService {

    @Autowired
    private HouseDataRepository repository;

    @Autowired
    private MongoOperations mongoOps;

    @Autowired
    private MessageChannel output;

//    MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));

//    @Autowired
//    public HouseDataProcessServiceImpl(HouseDataRepository repository) throws UnknownHostException {
//        this.repository = repository;
//    }

    @Override
    public HouseData get() {
        return null;
    }


    @Override
    public void save(HouseData house) {
        HouseData tmp = this.repository.findSameHouse(house);
        if (tmp == null) {
            this.repository.save(house);
            return;
        }
        this.repository.update(tmp, house);
        log.info(String.format("find duplicate house: [Old]$%f @%s, [New]$%f @%s,",
                tmp.getPrice(), tmp.getRawAddress(),
                house.getPrice(), house.getRawAddress()));
        if (tmp.getPrice() != house.getPrice()) {
            log.info(String.format("before sending house: $%f @%s", house.getPrice(), house.getRawAddress()));
            this.output.send(MessageBuilder.withPayload(house).build());
        }
    }
}
