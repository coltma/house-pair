package demo.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HouseSubscriptionRepositoryCustom {
    Subscription findSameSubscription(Subscription data);
    List<Subscription> findTargetSubscriptions(HouseData house);
}
