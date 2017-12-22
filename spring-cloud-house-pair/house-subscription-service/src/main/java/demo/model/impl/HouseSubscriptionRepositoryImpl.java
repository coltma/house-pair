package demo.model.impl;

import demo.model.HouseData;
import demo.model.HouseSubscriptionRepositoryCustom;
import demo.model.Subscription;
import demo.service.impl.HouseSubscriptionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HouseSubscriptionRepositoryImpl implements HouseSubscriptionRepositoryCustom {

    private static final int VALID_DAYS = 30;

    @Autowired
    private MongoOperations mongoOps;

    private MongoTemplate mongoTemplate;



    /**
     * Same: location, bedroom, bathroom, areaSize, houseNumber, emailAddress
     * @param data
     * @return
     */
    @Override
    public Subscription findSameSubscription(Subscription data) {
        Point point = new Point(data.getLocation().getX(), data.getLocation().getY());

        Subscription last = mongoOps.findOne(
                Query.query(
                    Criteria.where("location").nearSphere(point).maxDistance(0).minDistance(0).andOperator(
                        Criteria.where("houseNumber").is(data.getHouseNumber()).andOperator(
                            Criteria.where("emailAddress").is(data.getEmailAddress()).andOperator(
                                Criteria.where("bedroom").is(data.getBedroom()).andOperator(
                                    Criteria.where("bathroom").is(data.getBathroom()).andOperator(
                                        Criteria.where("areaSize").is(data.getAreaSize())
                                    )
                                )
                            )
                        )
                    )
                )
                ,
                Subscription.class);
        return last;
//        Date lastDate = last.getCreatedDate();
//        Date crtDate = data.getCreatedDate();
//        long diff = crtDate.getTime() - lastDate.getTime();
//        log.info(String.format("[Subscription Service] duplicate days diff: %d", diff / 60 * 60 * 24));
//        if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > VALID_DAYS) {
//
//            return false;
//        }
    }

    /**
     * Target subscriptions
     * @param house
     * @return
     */
    @Override
    public List<Subscription> findTargetSubscriptions(HouseData house) {
        Point point = new Point(house.getLocation().getX(), house.getLocation().getY());
        List<Subscription> targets = mongoOps.find(
                Query.query(
                    Criteria.where("location").nearSphere(point).maxDistance(0).minDistance(0).andOperator(
                        Criteria.where("houseNumber").is(house.getHouseNumber()).andOperator(
                            Criteria.where("bedroom").is(house.getBedroom()).andOperator(
                                Criteria.where("bathroom").is(house.getBathroom()).andOperator(
                                    Criteria.where("areaSize").is(house.getAreaSize())
                                )
                            )
                        )
                    )
                ),
                Subscription.class);
        return targets;
    }
}
