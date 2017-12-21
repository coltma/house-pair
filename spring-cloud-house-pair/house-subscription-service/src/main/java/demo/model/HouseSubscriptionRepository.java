package demo.model;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseSubscriptionRepository extends MongoRepository<Subscription, String> {

    List<Subscription> findByLocationNear(Point location, Distance min, Distance max);


}
