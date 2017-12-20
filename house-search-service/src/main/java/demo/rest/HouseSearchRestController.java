package demo.rest;

import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.model.PredictedPrice;
import demo.service.HousePricePredictionService;
import demo.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class HouseSearchRestController {

    private HouseService houseService;

    private HousePricePredictionService pricePredictionService;

    @Autowired
    public HouseSearchRestController(HouseService houseService, HousePricePredictionService pricePredictionService) {
        this.houseService = houseService;
        this.pricePredictionService = pricePredictionService;
    }

    @RequestMapping(value = "/houses/", method = RequestMethod.GET)
    public List<HouseData> housesWithin(@RequestParam(name = "lat") double lat,
                                        @RequestParam(name = "lng") double lng,
                                        @RequestParam(name = "radius") double radius) {
        return this.houseService.findByLocationWithin(lat, lng, radius);
    }

    @RequestMapping(value = "/predict", method = RequestMethod.GET)
    public String predict() {
        PredictedPrice price = this.pricePredictionService.predict();
        log.info(String.format("Get Price %s", price.getResult()));

        return "ss";
    }
}
