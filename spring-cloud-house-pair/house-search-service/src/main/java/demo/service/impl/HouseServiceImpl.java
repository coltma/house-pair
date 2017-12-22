package demo.service.impl;


import demo.model.HouseData;
import demo.model.SubscriptionRequest;
import demo.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class HouseServiceImpl implements HouseService {
    // in miles.
    private static final double earthRadius = 3963.2;

    private static final String houseDataProcessService = "http://house-data-process-service/";

    private static final String houseSubscriptionService = "http://house-subscription-service/";

    private RestTemplate restTemplate;

    @Autowired
    public HouseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Page<HouseData> findByCounty(String county, Pageable pageable) {
        return null;
    }

    @Override
    public List<HouseData> findByLocationWithin(double lat, double lng, double radius) {
        String parameters = String.format("houses/?lat=%f&lng=%f&radius=%f", lat, lng, radius);
        ResponseEntity<List<HouseData>> houseResponse =
                this.restTemplate.exchange(houseDataProcessService + parameters,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<HouseData>>() {
                        });
        return houseResponse.getBody();
//        // X for longitude, Y for latitude in Mongodb.
//        return this.repository.findByLocationWithin(new Circle(lng, lat, radius / earthRadius));

    }

    @Override
    public HouseData findById(String id) {
        String parameters = String.format("house/%s", id);
        return this.restTemplate.getForObject(houseDataProcessService + parameters, HouseData.class);
    }

    @Override
    public void subscribe(SubscriptionRequest subscription) {
        log.info(String.format("Post subscription: [%s].", subscription));
        this.restTemplate.postForLocation(houseSubscriptionService + "subscription", subscription, SubscriptionRequest.class);
    }

}
