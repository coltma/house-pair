package demo.rest;

import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseSearchRestController {

    private HouseService service;

    @Autowired
    public HouseSearchRestController(HouseService service) {
        this.service = service;
    }

    @RequestMapping(value = "/houses/", method = RequestMethod.GET)
    public List<HouseData> housesWithin(@RequestParam(name = "lat") double lat,
                                        @RequestParam(name = "lng") double lng,
                                        @RequestParam(name = "radius") double radius) {
        return this.service.findByLocationWithin(lat, lng, radius);
    }

}
