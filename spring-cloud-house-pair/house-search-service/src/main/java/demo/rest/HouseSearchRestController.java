package demo.rest;

import demo.model.HouseData;
import demo.model.HouseDataDto;
import demo.model.PredictedPrice;
import demo.model.SubscriptionRequest;
import demo.service.HousePricePredictionService;
import demo.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class HouseSearchRestController {

    private HouseService houseService;

    private HousePricePredictionService pricePredictionService;

    @Autowired
    public HouseSearchRestController(HouseService houseService,
                                     HousePricePredictionService pricePredictionService) {
        this.houseService = houseService;
        this.pricePredictionService = pricePredictionService;
    }

    @RequestMapping(value = "/houses", method = RequestMethod.GET)
    public List<HouseData> housesWithin(@RequestParam(name = "lat") double lat,
                                        @RequestParam(name = "lng") double lng,
                                        @RequestParam(name = "radius") double radius) {
        return this.houseService.findByLocationWithin(lat, lng, radius);
    }

    @RequestMapping(value = "/house/{id}", method = RequestMethod.GET)
    public HouseDataDto findHouse(@PathVariable String id) {
        HouseData house = this.houseService.findById(id);
        PredictedPrice price = null;
        if (house != null) {
            price = this.pricePredictionService.predict(house.getBedroom(),
                    house.getBathroom(), house.getAreaSize());
        }
        double predictPrice = price == null ? 0 : price.getResult();
        HouseDataDto data = new HouseDataDto(house, predictPrice);
        return data;
    }

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribeHouse(@RequestBody SubscriptionRequest subscriptionRequest) {
        this.houseService.subscribe(subscriptionRequest);
    }

}
