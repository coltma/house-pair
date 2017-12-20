package demo.service.impl;

import demo.model.PredictedPrice;
import demo.service.HousePricePredictionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class HousePricePredictionServiceImpl implements HousePricePredictionService {

//    private RestTemplate restTemplate;

//    @Autowired
//    public HousePricePredictionServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    // @HystrixCommand(fallbackMethod = "xxx")
    @Override
    public PredictedPrice predict() {
        final String url = "http://localhost:8000/prediction/";
        String test =  "?br=1.0&ba=1.0&area=700&postal=90007&lat=33.984809&lng=-118.445492";
        log.info(String.format("Calling django REST API"));
//        PredictedPrice price = this.restTemplate.getForObject(url + test, PredictedPrice.class);
        return null;
    }
}
