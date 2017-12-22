package demo.service;

import demo.model.HouseData;
import demo.model.SubscriptionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HouseService {
    Page<HouseData> findByCounty(String county, Pageable pageable);

    List<HouseData> findByLocationWithin(double lat, double lng, double radius);

    HouseData findById(String id);

    void subscribe(SubscriptionRequest subscription);

}
