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
    /**
     * same house: location, houseNumber, bedroom, bathroom, areaSize
     */
    public HouseData findSameHouse(HouseData current) {
        Point point = new Point(current.getLocation().getX(), current.getLocation().getY());
        HouseData last = mongoOps.findOne(
                Query.query(Criteria.where("location").nearSphere(point).maxDistance(0).minDistance(0)
                    .andOperator(
                        Criteria.where("houseNumber").is(current.getHouseNumber())
                        .andOperator(
                            Criteria.where("bedroom").is(current.getBedroom())
                            .andOperator(
                                Criteria.where("bathroom").is(current.getBathroom())
                                .andOperator(
                                    Criteria.where("areaSize").is(current.getAreaSize())
                                )
                            )
                        )
                    )), HouseData.class);
        if (last != null) {
            log.info(String.format("[HouseDataProcessService] find same house: %f @%s", last.getPrice(), last.getRawAddress()));
            log.info(String.format("[HouseDataProcessService] to update house: %f @%s", current.getPrice(), current.getRawAddress()));
        }
        return last;
    }

    @Override
    public void save(HouseData house) {
        HouseData tmp = findSameHouse(house);
        this.repository.save(house);
        //TODO: log
//        this.output.send(MessageBuilder.withPayload(house).build());
    }
}
