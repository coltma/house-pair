package demo.service.impl;

import com.mongodb.MongoClient;
import demo.model.HouseSubscriptionRepository;
import demo.model.Subscription;
import demo.service.HouseSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class HouseSubscriptionServiceImpl implements HouseSubscriptionService {

    private static final int VALID_DAYS = 30;

    private HouseSubscriptionRepository repository;

    @Autowired
    public HouseSubscriptionServiceImpl(HouseSubscriptionRepository repository) throws UnknownHostException {
        this.repository = repository;
    }

    @Override
    public void subscribe(Subscription data) {
        Subscription last = this.repository.findSameSubscription(data);
        if (last == null) {
            this.repository.save(data);
            return;
        }
        log.info(String.format("Duplicated: %s, %s @ %s",
                last.getEmailAddress(), last.getLocation(), last.getCreatedDate()));
    }


//    /**
//     * is Subscribed:
//     * (1) same position and houseNumber;
//     * (2) createdDate within 30 days;
//     * @param data
//     * @return
//     */
//    @Override
//    public boolean isSubscribed(Subscription data) {
//        Point point = new Point(data.getLocation().getX(), data.getLocation().getY());
//
//        Subscription last = mongoOps.findOne(
//                Query.query(Criteria.where("location").nearSphere(point).maxDistance(0).minDistance(0).andOperator(
//                                Criteria.where("houseNumber").is(data.getHouseNumber()).andOperator(
//                                    Criteria.where("emailAddress").is(data.getEmailAddress())))),
//                Subscription.class);
//        if (last == null) {
//            return false;
//        }
//        Date lastDate = last.getCreatedDate();
//        Date crtDate = data.getCreatedDate();
//        long diff = crtDate.getTime() - lastDate.getTime();
//        log.info(String.format("[Subscription Service] duplicate days diff: %d", diff / 60 * 60 * 24));
//        if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > VALID_DAYS) {
//            // Lazy clean
//            this.repository.delete(last.getId());
//            return false;
//        }
//
//        log.info(String.format("[Subscription Service] duplicated: %s, %s @ %s",
//                last.getEmailAddress(), last.getLocation(), last.getCreatedDate()));
//        return true;
//    }
}
