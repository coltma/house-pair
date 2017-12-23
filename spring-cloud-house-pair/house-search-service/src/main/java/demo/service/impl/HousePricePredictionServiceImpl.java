package demo.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.PredictedPrice;
import demo.service.HousePricePredictionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@EnableCircuitBreaker
public class HousePricePredictionServiceImpl implements HousePricePredictionService {
    private static final String predictUrl = "http://localhost:8000/prediction/";

    private RestTemplate rawRestTemplate = new RestTemplate();

    @HystrixCommand(fallbackMethod = "getPredictPriceFallBack")
    @Override
    public PredictedPrice predict(double bedroom, double bathroom, double areaSize) {
        String params = String.format("?br=%f&ba=%f&area=%f", bedroom, bathroom, areaSize);
        log.info(String.format("Calling django REST API"));
        return this.rawRestTemplate.getForObject(predictUrl + params, PredictedPrice.class);
    }

    public void getPredictPriceFallBack(double bedroom, double bathroom, double areaSize) {
        log.error("Hystrix Fallback Method. Unable to send house feature to django.");
    }
}
